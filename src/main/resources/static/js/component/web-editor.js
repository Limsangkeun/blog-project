class WebEditor extends HTMLElement { // (1)

	constructor () {
		super();
	}

    connectedCallback() {
     const editorBox = document.createElement('div');
     this.append(editorBox);
      const editor = new toastui.Editor({
        el: editorBox,
        height: '500px',
        initialEditType: 'markdown',
        previewStyle: 'vertical'
      });
      editor.getMarkdown();
    }
  
  }
  
  customElements.define("web-editor", WebEditor);