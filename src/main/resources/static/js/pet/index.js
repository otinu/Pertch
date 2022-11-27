window.addEventListener('load', () => {
  
  const button = document.getElementById('deleteBtn');
  
  // HTML 要素に対して、イベントのハンドラー関数を登録する
	button.addEventListener('click', deleteAlert);
	
	const petShow = document.getElementById('pet-show');
	petShow.addEventListener('click', () => { petShow.submit() })
	
});

const deleteAlert = function(event) {
	if(!confirm('削除しますか？')) return event.preventDefault()
}

