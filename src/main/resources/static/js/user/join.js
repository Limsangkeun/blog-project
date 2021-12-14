const join = {
    init: () => {
        document.querySelector('#btn-join').onclick = (e) => {
            this.join();
        }
    },
    join: () => {
        alert('join!');
    }

}

join.init();