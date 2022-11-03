window.addEventListener('load', () => {
  
  // const button = document.querySelector('#delete');
  const button = document.getElementById('deleteBtn');
  
  // HTML 要素に対して、イベントのハンドラー関数を登録する
	button.addEventListener('click', deleteAlert);
});

const deleteAlert = function(event) {
	if(!confirm('削除しますか？')) return event.preventDefault()
}

