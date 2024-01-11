/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
function toast( {
title = '',
        message = '',
        type = '',
        duration = 3000
        }){
    const main = document.querySelector('#toast');
    if (main) {
        const toast = document.createElement('div');
        const delay = (duration / 1000).toFixed(2);

        const hidenTime = setTimeout(function () {
            toast.style.animation = `slideRight ease .7s, fadeOut ease 1s ${delay}`;
        }, duration + 500);

        const autoRemove = setTimeout(function () {
            main.removeChild(toast);
        }, duration + 1000);

        toast.onclick = function (e) {
            clearTimeout(hidenTime);
            clearTimeout(autoRemove);
            toast.style.animation = `slideRight ease .7s, fadeOut ease 1s 0`;
            const closeTag = setTimeout(function () {
                main.removeChild(toast);
            }, 650);
            if (e.target.closest('.toast-close')) {
                clearTimeout(closeTag);
                main.removeChild(toast);
            }
        };
        const icons = {
            success: 'fa-check-circle',
            warning: 'fa-exclamation-circle',
            error: 'fa-exclamation-circle',
            info: 'fa-info-circle'
        };
        const icon = icons[type];
        toast.classList.add('toast-custom', `toast--${type}`);
        toast.style.animation = `slideLeft ease .7s`;
        toast.innerHTML = `
                <div class="toast-icon">
                    <i class="fa ${icon}"></i>
                </div>
                <div class="flex-grow-1">   
                    <h3 class="toast-titile">${title}</h3>
                    <p class="toast-msg">${message}</p>
                </div>
                <div class="toast-close">
                    <i class="fa fa-times"></i> 
                </div> 
                <div class="toast-timebar">
                </div> 
        `;
        main.appendChild(toast);
}
}

