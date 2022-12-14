const deleteAlert = function(event) {
  const confirm = window.confirm("削除します\n内容をご確認ください")
    if (!confirm) {
      event.stopPropagation();
      event.preventDefault();
    }
}

document.addEventListener("DOMContentLoaded", function() {
  
  const deleteButtons = document.body.querySelectorAll("#deleteBtn");
  
  // HTML 要素に対して、イベントのハンドラー関数を登録する
  deleteButtons.forEach(function(deleteButton) {
    deleteButton.addEventListener('click', deleteAlert);
  });
  
  const petShows = document.body.querySelectorAll('#pet-show');
  petShows.forEach(function(petShow) {
    petShow.addEventListener('click', () => { petShow.submit() });
  });
  

});