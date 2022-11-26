const submitHandle = (event) => {
	const confirm = window.confirm("変更を破棄し、TOP画面に移動します")
	if (!confirm) {
		event.stopPropagation();
		event.preventDefault();
	}
}

document.addEventListener("DOMContentLoaded", function() {
	let topButton = document.body.querySelector(".top-button")
	let confirm = true
	topButton.addEventListener('submit', submitHandle)
	// return confirm
})