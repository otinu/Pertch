const deleteAlert = function(event) {
  const confirm = window.confirm("削除しますか？")
    if (!confirm) {
      event.stopPropagation();
      event.preventDefault();
    }
}

document.addEventListener("DOMContentLoaded", function() {
  
  const button = document.body.querySelector("#deleteBtn")
  
  // HTML 要素に対して、イベントのハンドラー関数を登録する
  button.addEventListener('click', deleteAlert);
  
  const petShow = document.getElementById('pet-show');
  petShow.addEventListener('click', () => { petShow.submit() })

});