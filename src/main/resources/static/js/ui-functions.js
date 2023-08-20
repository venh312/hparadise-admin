const loadingDisplay = function(flag) {
  document.getElementById('loading').style.display = flag;
}

const changeDate = function(obj) {
  const startDate = document.getElementById('startDate');
  const endDate = document.getElementById('endDate');
  if (startDate.value && endDate.value) {
    if (obj.id == 'startDate' && startDate.value.replace(/-/g, '') > endDate.value.replace(/-/g, ''))
      endDate.value = startDate.value;
    else (startDate.value.replace(/-/g, '') > endDate.value.replace(/-/g, ''))
      startDate.value = endDate.value;
  }
}

const fnPageMove = function(obj) {
  const pageItem = document.querySelectorAll('.page-item');
  pageItem.forEach(function(element, index) {
    element.classList.remove('active');
  });
  obj.parentNode.classList.add('active');
  document.getElementById('page').value = obj.getAttribute('data-page');
  document.getElementById('pageSize').value = obj.getAttribute('data-pageSize');
  fnSearch();
}

const fnPageItemHtml = function(resultMap) {
  let html = '<li class="page-item first" id="pageItemFirst">';
  html += '<button class="page-link" data-page="0" data-pageSize="8" onclick="fnPageMove(this)">';
  html += '<i class="tf-icon bx bx-chevrons-left"></i>';
  html += '</button>';
  html += '</li>';

  for (var i = resultMap.startPage; i <= resultMap.endPage; i++) {
    let page = resultMap.pageSize * (i - 1);
    if (resultMap.currentPage == i)
      html += '<li class="page-item active">';
    else
      html += '<li class="page-item">';
    html += '<button class="page-link" id="pageLink" data-page="'+page+'" data-pageSize="'+resultMap.pageSize+'" onclick="fnPageMove(this)">'+i+'</button>';
    html += '</li>';
  }

  const lastPage = (resultMap.totalCnt - resultMap.pageSize);
  if (lastPage >= resultMap.pageSize) {
    html += '<li class="page-item last">';
    html += '<button class="page-link" data-page="'+(resultMap.totalCnt - resultMap.pageSize)+'" data-pageSize="'+resultMap.pageSize+'" onclick="fnPageMove(this)">';
    html += '<i class="tf-icon bx bx-chevrons-right"></i>';
    html += '</button>';
    html += '</li>';
  }

  document.getElementById('pagination').innerHTML = html;
}

const fnTrAllChk = function() {
  const tr = document.getElementsByName('trChk');
  tr.forEach(element => {
    if (element.checked) {
      element.checked = false;
    } else {
      element.checked = true;
    }
  });
}

document.addEventListener('DOMContentLoaded', function() {
  const birthInput = document.getElementById('birth');
  if (birthInput) {
    birthInput.addEventListener('input', () => {
      let value = birthInput.value.replace(/\D/g, ''); // 숫자 이외의 문자 제거
      if (value.length > 8) value = value.slice(0, 8); // 최대 길이를 8자로 제한
      if (value.length >= 4 && value.length < 6) value = `${value.slice(0, 4)}-${value.slice(4)}`;
      else if (value.length >= 6) value = `${value.slice(0, 4)}-${value.slice(4, 6)}-${value.slice(6)}`;
      birthInput.value = value;
    });
  }

  // [lnb] 현재 menu item active 처리
  const lnbMenu = document.querySelectorAll('.menu-item');
  lnbMenu.forEach(element => {
    let path = location.pathname.split('/');
    if (element.className.indexOf(path[1]) > -1) element.classList.add('active')
    else element.classList.remove('active');
  });

  if (location.pathname.indexOf('list') > -1) {
    document.getElementById('search').addEventListener('keyup', function(event) {
      if (event.key === "Enter") fnSearch();
    });
  }
});

