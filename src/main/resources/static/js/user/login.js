const login = {
    init: () => {
        document.querySelector('#btn-login').addEventListener('click', ()=> {
            login.loginProcess();
        });
    },
    loginProcess: () => {
        const form = document.querySelector('#form-login');
        form.submit();
    }
}

login.init();