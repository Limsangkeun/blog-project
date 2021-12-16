const join = {
    init: () => {
        document.querySelector('#btn-join').onclick = (e) => {
            join.join();
        }
    },
    join: () => {
		const form = document.querySelector('#join-form');
		const formData = new FormData(form);
	
		fetch('/user/join', {
			method: 'post',
			body: formData
		}).then(response => response.json())
		.then(jsonData => {
			Swal.fire(jsonData.message).then(() => {
				if(jsonData.result === 0) {
					return;
				}
				location.href = "/user/login";
			});
		}).catch(e=>{
			console.error(e);
		});	
    }

}

join.init();