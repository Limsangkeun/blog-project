window.onload=()=>{document.querySelector("header").querySelectorAll("a").forEach(((e,o,l)=>{e.addEventListener("click",(o=>{o.preventDefault(),console.log(e.href)}))}))};