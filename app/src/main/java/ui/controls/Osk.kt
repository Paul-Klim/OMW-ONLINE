fun placeKeyboard(isRussian: Boolean) {
    // Уменьшенные размеры кнопок, увеличенные промежутки
    val buttonWidth = 35  // уменьшенная ширина кнопки
    val buttonHeight = 45 // уменьшенная высота кнопки
    val buttonMarginX = 6 // увеличенный горизонтальный промежуток
    val buttonMarginY = 4 // увеличенный вертикальный промежуток
    
    // Смещение клавиатуры вправо и вниз
    val offsetX = VIRTUAL_SCREEN_WIDTH - (buttonWidth * 15) // смещение вправо
    val offsetY = VIRTUAL_SCREEN_HEIGHT - (buttonHeight * 6) // смещение вниз

    var curX: Int
    var curY = offsetY

    var keyboardLayout: ArrayList<String>

    if(isRussian) {
        keyboardLayout = arrayListOf(
                "1!2@3#4$5%6^7&8*9(0)-_=+",
                "йЙцЦуУкКеЕнНгГшШщЩзЗхХ[{]}\\|",
                "фФыЫвВаАпПрРоОлЛдДжЖэЭ;:'\"",
                "яЯчЧсСмМиИтТьЬбБюЮ,<.>/?"
        )
    } else {
        keyboardLayout = arrayListOf(
                "1!2@3#4$5%6^7&8*9(0)-_=+",
                "qQwWeErRtTyYuUiIoOpP[{]}\\|",
                "aAsSdDfFgGhHjJkKlL;:'\"",
                "zZxXcCvVbBnNmM,<.>/?"
        )
    }

    val lineOffset = arrayOf(
            (offsetX + buttonWidth * 1.0 + buttonMarginX).toInt(),
            (offsetX + buttonWidth * 0.5 + buttonMarginX).toInt(),
            (offsetX + buttonWidth * 1.25 + buttonMarginX).toInt(),
            (offsetX + buttonWidth * 1.5 + buttonMarginX).toInt()
    )

    val simpleButtons = ArrayList<OskSimpleButton>()
    keyboardLayout.forEachIndexed{ i, line ->
        curX = lineOffset[i]

        for (j in 0..(line.length - 1) step 2) {
            simpleButtons.add(OskSimpleButton(line[j], line[j + 1], curX, curY, buttonWidth, buttonHeight))
            curX += buttonWidth + buttonMarginX
        }
        curY += buttonHeight + buttonMarginY
    }
    elements.addAll(simpleButtons)

    // Shift
    elements.add(OskShift(simpleButtons, 
        offsetX, 
        offsetY + 3 * (buttonHeight + buttonMarginY), 
        (buttonWidth * 1.5).toInt(), 
        buttonHeight
    ))

    // Capslock
    elements.add(OskCaps(simpleButtons, 
        offsetX, 
        offsetY + 2 * (buttonHeight + buttonMarginY), 
        (buttonWidth * 1.25).toInt(), 
        buttonHeight
    ))

    // Backspace
    elements.add(OskRawButton(
            "⌫",
            KeyEvent.KEYCODE_DEL,
            lineOffset[0] + (buttonWidth + buttonMarginX) * keyboardLayout[0].length / 2,
            offsetY,
            buttonWidth * 2,
            buttonHeight
    ))

    // Enter
    elements.add(OskRawButton(
            "⏎",
            KeyEvent.KEYCODE_ENTER,
            lineOffset[2] + (buttonWidth + buttonMarginX) * keyboardLayout[2].length / 2,
            offsetY + (buttonHeight + buttonMarginY) * 2,
            buttonWidth * 2,
            buttonHeight
    ))

    // Language
    elements.add(OskLanguage(this, 
        offsetX, 
        curY, 
        (buttonWidth * 1.5).toInt(), 
        buttonHeight
    ))

    // Spacebar
    elements.add(OskSimpleButton(' ', ' ', 
        offsetX + buttonWidth * 3, 
        curY, 
        buttonWidth * 6, 
        buttonHeight
    ))

    // Arrows
    var arrowsCurX = lineOffset[3] + (buttonWidth + buttonMarginX) * keyboardLayout[3].length / 2 + buttonWidth
    if (isRussian)
        arrowsCurX -= 15
    var arrowsCurY = offsetY + (buttonHeight + buttonMarginY) * 3

    // Стрелка вверх
    elements.add(OskRawButton("↑", 
        KeyEvent.KEYCODE_DPAD_UP, 
        arrowsCurX, 
        arrowsCurY, 
        buttonWidth, 
        buttonHeight
    ))

    arrowsCurX -= buttonWidth + buttonMarginX
    arrowsCurY += buttonHeight + buttonMarginY

    // Стрелка влево
    elements.add(OskRawButton("←", 
        KeyEvent.KEYCODE_DPAD_LEFT, 
        arrowsCurX, 
        arrowsCurY, 
        buttonWidth, 
        buttonHeight
    ))

    arrowsCurX += buttonWidth + buttonMarginX
    
    // Стрелка вниз
    elements.add(OskRawButton("↓", 
        KeyEvent.KEYCODE_DPAD_DOWN, 
        arrowsCurX, 
        arrowsCurY, 
        buttonWidth, 
        buttonHeight
    ))

    arrowsCurX += buttonWidth + buttonMarginX
    
    // Стрелка вправо
    elements.add(OskRawButton("→", 
        KeyEvent.KEYCODE_DPAD_RIGHT, 
        arrowsCurX, 
        arrowsCurY, 
        buttonWidth, 
        buttonHeight
    ))

    // Тильда
    elements.add(OskRawButton("~", 
        68, 
        offsetX, 
        offsetY, 
        buttonWidth, 
        buttonHeight
    ))
}
