class TitleBar extends HTMLElement { // (1)

    connectedCallback() {
      const title = this.getAttribute('title');

      const titleBar = document.createElement('div');
      titleBar.style.display = 'flex';
      titleBar.style.justifyContent = "space-between";

      const h3 = document.createElement('h3');
      h3.innerText = title;
      h3.style.flex = 1;

      const btnBox = document.createElement('div');
      
      const buttonCount = this.children.length;
      for(let i=0; i<buttonCount; i++) {
		  let btn = this.children[0];
		  
		  btn.classList.add('btn', 'btn-primary');
	      btnBox.append(btn);
	  }

      titleBar.append(h3);
      titleBar.append(btnBox);

      this.append(titleBar);
    }
  
  }
  
  customElements.define("title-bar", TitleBar);