
file = open("темная_пони.txt")
lines = file.read().split("\n")
file.close()

chapters = [
	{'id' : 0, 'title' : "0. Зло" },
	{'id' : 1, 'title' : "1. Две подруги" },
	{'id' : 2, 'title' : "2. Летние каникулы"},
	{'id' : 3, 'title' : "3. Твайлайт Спаркл"},
	{'id' : 4, 'title' : "4. Вступительные экзамены"},
	{'id' : 5, 'title' : "5. Мистер Магитемз"},
	{'id' : 6, 'title' : "6. Эмбер Грим"},
	{'id' : 7, 'title' : "7. Вечеринка"},
	{'id' : 8, 'title' : "8. Конец дружбы"},
	{'id' : 9, 'title' : "9. Сестра"},
	{'id' : 10, 'title' : "10. Школа Дружбы"},
	{'id' : 11, 'title' : "11. Магическая дуэль"},
	{'id' : 12, 'title' : "12. Дружеская беседа"},
	{'id' : 12, 'title' : "12. Извинения"},
	{'id' : 13, 'title' : "13. Последняя беседа"},
	{'id' : 14, 'title' : "14. Осенний забег"},
	{'id' : 15, 'title' : "15. Тайна происхождения"},
	{'id' : 16, 'title' : "16. Расследование"},
	{'id' : 17, 'title' : "17. Оборотни"},
	{'id' : 18, 'title' : "18. История оборотней"},
	{'id' : 19, 'title' : "19. Колокольчик Грогара"},
	{'id' : 20, 'title' : "20. Темная пони"}
]

filtered_lines = [x.strip() for x in lines if x]

chapter = 0
text = ""
for line in filtered_lines:
	if chapter < 20 and chapters[chapter]['title'] == line:
		if chapter > 0:
			text += "))\n"
		text += "put({}, listOf(\n".format(chapters[chapter]['id'])
		chapter += 1
	else:
		text += '"{}",\n'.format(line)

code = open("code.txt", 'w')
code.write(text)
code.close()	
