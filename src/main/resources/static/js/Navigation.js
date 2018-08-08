export default class Navigation extends HTMLElement {
    constructor() {
        super();
        const HOME = 'home';
      
       }


    connectedCallback() {

        // Load once on page load, to navigate to the hash
        this.navigate();

        // Navigate whenever the fragment identifier changes it's value
        window.onhashchange = _ => this.navigate();
    }

    // Update the content for the fragment identifier
    navigate() {
        console.log("==> START navigate().");

        const fragmentId = this.getFragmentId();

        // Set the content according to the framgmentId
        const contentDiv = document.querySelector('#content');
        this.getContent(fragmentId, content => contentDiv.innerHTML = content);

        this.setActiveLink(fragmentId);

        console.log("<== END navigate().");
    }

    // Sets the "active" class on the active navigation link (the given fragment
    // identifier)
    setActiveLink(fragmentId) {
        const links = document.querySelectorAll('.nav-link');
        links.forEach(link => {
            link.classList.remove('active');
            let pageName = link.getAttribute("href").substr(1);
            if (pageName === fragmentId) {
                link.classList.add('active');
            }
        });
    }

    // Returns the content for the given fragment identifier.
    getContent(fragmentId, callback) {

        let uri = `partials/${fragmentId}/${fragmentId}.html`;

        fetch(uri)
            .then(resp => {
                if (resp.status === 404) {
                    console.log(`The requested resource ${uri} could not be loaded.`);
                    location.hash = "404";
                    this.navigate();
                }
                return resp.text();
            })
            .then(data => callback(data))
            .catch(error => console.log(`Something went wrong loading ${uri}. This is, when the server is down.....`)); 
    }

    /* Returns the fragment hash from the location.
     If no hash is present, we use the HOME value.*/ 
    getFragmentId() {

        let hash = location.hash;

        // Set "home" as the default navigation, if nothing is specified in the URL 
        if (!hash || hash === '#') {
            return this.HOME;
        }

        return hash.substr(1);
    }


}

customElements.define('stock-navigate', Navigation);