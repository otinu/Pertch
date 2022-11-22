const messageSecond = "令和3年度 環境省 統計資料「犬・猫の引取り及び負傷動物等の収容並びに処分の状況」より"
const messageThird = "誰か 見かけませんでしたか？"
const cursorFilePath = "/img/cursor3.png"
const foundPetFilePath = "/img/found_pet.png"


const windowWidth = window.innerWidth
const windowHeight = window.innerHeight
const windowSizeTAB = 800
const windowSizeSP = 420

class pixelObject {
    x = 0
    y = 0
    pixelObject(x, y) {
        this.x = x
        this.y = y
    }
    translateString() {
        return "(" + this.x + "px" + "," + this.y + "px)"
    }
}

// カーソルアニメーションの動きを乱数で作成
function makeRandomPixel() {
    let array = []
    for (let i = 0; i < 6; i++) {
        let pixel = new pixelObject()
        if (i == 0) {
            // 「x = y = 乱数生成」としてしまうと動きに規則性が生まれてしまうため、x・yにそれぞれ代入
            pixel.x = Math.random() * 100 * 10
            pixel.y = Math.random() * 100 * 5
        } else if (i % 2 == 0) {
            pixel.x = Math.random() * 100 * (i + 2) * 2
            pixel.y = Math.random() * 100 * (i + 2)
        } else if (i % 2 == 1) {
            pixel.x = Math.random() * 100 * i * 2
            pixel.y = Math.random() * 100 * i
        }
        array[i] = pixel
    }
    return array
}

// 乱数生成時、画面外へのアニメーションはみ出し防止
function compressionIntoWindow(randomPixel, imgWidth, imgHeight) {
    let maxWidth = window.innerWidth - imgWidth
    let maxHeight = window.innerHeight - imgHeight
    for (let i = 0; i < randomPixel.length; ++i) {
        if (randomPixel[i].x > maxWidth) { randomPixel[i].x = maxWidth }
        if (randomPixel[i].y > maxHeight) { randomPixel[i].y = maxHeight }
    }
    return randomPixel
}

// randomPixelは複数スコープに跨るため、グローバル変数で作成
var randomPixel = []
randomPixel = makeRandomPixel()

document.addEventListener("DOMContentLoaded", function () {

    let timer = 10000
    const slideTitle = document.querySelector('#message-area')
    setTimeout(() => {
        slideTitle.classList.remove("animate-title-first")
        slideTitle.classList.add("animate-title-second")
        slideTitle.innerHTML = "<p class='string-second'>" + messageSecond + "</p>"
    }, timer)
    setTimeout(() => {
        slideTitle.classList.remove("animate-title-second")
        slideTitle.classList.add("animate-title-third")
        slideTitle.innerHTML = "<h2 class='string-third'>" + messageThird + "</h2>"
    }, timer + 7000)
    setTimeout(() => {
        slideTitle.classList.remove("animate-title-second")
        slideTitle.classList.add("animate-title-third"  )
        slideTitle.innerHTML = "<img class='search-cursor' src='" + cursorFilePath +"'>"

        slideTitle.remove()
        document.querySelector('#container').remove()
        document.body.innerHTML = "<img class='search-cursor' src='" + cursorFilePath + "'>"
        const cursor = document.querySelector('.search-cursor')

        cursor.addEventListener('load', (e) => {
            let imgWidth = e.target.width
            let imgHeight = e.target.height

            randomPixel = compressionIntoWindow(randomPixel, imgWidth, imgHeight)
            cursor.animate(
                {
                    opacity: [1, 0],
                    // opacity: 1,
                    transform: [
                        "translate" + randomPixel[0].translateString(),
                        "translate" + randomPixel[1].translateString(),
                        "translate" + randomPixel[2].translateString(),
                        "translate" + randomPixel[3].translateString(),
                        "translate" + randomPixel[4].translateString(),
                        "translate" + randomPixel[5].translateString()
                    ]
                },
                {
                    //持続時間を乱数を使って設定
                    duration: 4000,
                    direction: 'alternate',
                    fill: 'forwards',
                    easing: 'ease-in-out'
                }
            )
        })

    }, timer + 11000)

    // !!!アイコンの表示座標
    let stringX = ""
    let stringY = ""
    // カーソルアニメーション 最終モーションでのpixelObject(座標)

    let lastRandomPixel = randomPixel.slice(-1)[0]
    setTimeout(() => {
        document.body.innerHTML = "<div class='fontAwesome'><i class='fa-solid fa-exclamation fa-5x fa-bounce'></i><i class='fa-solid fa-exclamation fa-5x fa-bounce'></i><i class='fa-solid fa-exclamation fa-5x fa-bounce'></i></div>"
        const exclamation = document.querySelector('.fontAwesome')

        if (lastRandomPixel.x > 330) {
            lastRandomPixel.x = 330
        }
        stringX = String(lastRandomPixel.x) + 'px'
        stringY = String(lastRandomPixel.y) + 'px'
        exclamation.style.paddingTop = stringY
        exclamation.style.paddingLeft = stringX
        exclamation.style.color = "white"
    }, timer + 15000)

    // ペット発見アイコン
    let foundPet = ""
    setTimeout(() => {
        document.body.innerHTML = ""
        document.body.innerHTML = "<div id='title-area'><img class='found-pet' src='" + foundPetFilePath + "'></div>"
        foundPet = document.querySelector('.found-pet')
        foundPet.style.paddingTop = stringY
        foundPet.style.paddingLeft = stringX

    }, timer + 16000)
    setTimeout(() => {
        let windowCenterX = windowWidth / 2
        let windowCenterY = windowHeight / 2

        // レスポンシブ対応(SP)
        if (windowSizeSP > windowWidth) { windowCenterX += 40 }

        let absoluteAddressX = windowCenterX - foundPet.width - foundPet.style.paddingLeft.replace("px", "")
        let absoluteAddressY = windowCenterY - foundPet.height - foundPet.style.paddingTop.replace("px", "")

        foundPet.animate(
            {
                transform: "translate(" + absoluteAddressX + "px" + "," + absoluteAddressY + "px)"
            },
            {
                duration: 1500,
                direction: 'alternate',
                fill: 'forwards',
                easing: 'ease-in-out'
            }
        )
        
    }, timer + 17000)

    setTimeout(() => {
        let afterMovingImg = document.querySelector('.found-pet')
        afterMovingImg.classList.remove('found-pet');
        afterMovingImg.setAttribute("id","logo")
        afterMovingImg.src = "/img/logo2.png"
        
        
    }, timer + 19000)

})