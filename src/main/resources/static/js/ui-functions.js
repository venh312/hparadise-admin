const loadingDisplay = function(flag) {
  document.getElementById('loading').style.display = flag;
}

document.addEventListener('DOMContentLoaded', function() {
  // [lnb] Current page add class active
  const lnbMenu = document.querySelectorAll('.menu-item');
  lnbMenu.forEach(element => {
    if (element.className.indexOf(location.pathname) > -1) {
      element.classList.add('active')
    } else {
      element.classList.remove('active');
    }
  });
});

