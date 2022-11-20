# RPKS

*** Заменить все стеки на  ArrayDeq ***

* [Lab 1](#Lab-1)
* [Lab 2](#Lab-2)            Сдана
* [Lab 3](#Lab-3)            Закончена, проверить все ли вхождения находит 
* [Lab 4](#Lab-4)            Закончена
* [Lab 5](#Lab-5)            Закончена

### Lab 1
чтобы не падало при не открытии файла, есть пометки...

Реализовать программу, которая выполняет проверку правильности
расставленных скобок. Скобки считаются правильно расставленными
если каждой открытой скобке соответствует закрытая скобка. Внутри
скобок и между скобок могут находиться любые данные. На вход
программе подается файл с конфигурациями, в котором можно указать
символы, являющиеся открывающейся и закрывающейся скобкой, к
примеру в формате json:
1. {“bracket”:[{“left”:”[”,”right”:”]”},{”left”:”{”,”right”:”}”}]}
  и файл, который нужно проверить на правильность расставленных
  скобок.
2. [some(exe{1!|value|2?}jar)none] – скобки расставлены правильно,
  если в конфигурации указаны скобки “[ ]”,”{ }”,”( )”, “| |”
  [some(one{1!|value|2?}jar))none] – скобки расставлены правильно,
  если в конфигурации указаны скобки “[ ]”,”{ }”, “| |” и
  неправильно, если указаны “[ ]”,”{ }”,”( )”, “| |”.
 
В случае успешного прохождения проверки программа должна
написать текст об успешной проверке, иначе, программа должна
указать в каком месте неверно указаны скобки (каким образом – на
усмотрение разработчика).
Пользовательский интерфейс для программы необязателен.

### Lab 2
 Реализовать программу, которая выполняет размен монет. На вход
программе подается сумма, которую нужно разменять, и номинал
монет. Реализовать с помощью рекурсии. Пример: нужно разменять 10
с доступными номиналами 4 и 1, в результате работы программы
должно быть указано 10 -> 4[2], 1[2] – две монеты по 4 и две монеты по
Если размен невозможен – программа должна написать, что размен
невозможен. Пользовательский интерфейс для программы
необязателен. 

### Lab 3
Поиск строки по ключевому слову. Реализуйте программу, которая
выполняет поиск строки в файле по заданному ключевому слову и
найденную строку записывает в результирующий файл. Должны быть
найдены и записаны все соответствующие строки. Размер файла может
   быть любой (от нескольких байт, до десятков гигабайт). Должна быть
   реализована возможность указать количество строк до и после
   найденной строки для записи в результирующий файл. Для увеличения
   скорости поиска нужно воспользоваться возможностями
   многопоточности. Необходимо отображать прогресс работы
   программы во время ее работы (сколько работает, сколько осталось
   работать, сколько найдено совпадений). Пользовательский интерфейс
   для программы необязателен.

### Lab 4
Реализовать программу, которая выводит на экран размер (в байтах)
   директории. Название директории передаётся через аргументы
   командной строки. Программа должна корректно обрабатывать ошибки
   без вывода Stack Trace и Exception.
   Пример запуска программы: java –jar SizeDirectory.jar /home/user/
   Вывод: /home/user ---- 100000000 bytes / 100000 Mb / 100 Gb

### Lab 5
Найти GAP в логах приложения. Реализуйте программу, которая ищет
пики отклоения по скорости выполнения запросов на основе логов
приложения. Лог приложения имеет дату и время начала запроса,
типовой текст начала выполнения запроса или окончание выполнения
запроса. Программа должна выполнить анализ файла с логами, выявить
среднее время выполнения запроса и выдать запросы, которые имеют
отклонение на заданную величину. Запросы в логах могут быть
асинхронные, пример:

a. 2019-09-01 00:05:00 – INFO – QUERY FOR ID = 1

b. 2019-09-01 00:05:01 – INFO – QUERY FOR ID = 2

c. 2019-09-01 00:05:03 – INFO – RESULT QUERY FOR ID = 1

d. 2019-09-01 00:05:10 – INFO – QUERY FOR ID = 4

e. 2019-09-01 00:05:12 – INFO – QUERY FOR ID = 3

f. 2019-09-01 00:05:13 – INFO – RESULT QUERY FOR ID = 3

g. 2019-09-01 00:05:13 – INFO – RESULT QUERY FOR ID = 4

h. 2019-09-01 00:05:30 – INFO – RESULT QUERY FOR ID = 2

Самый аномальный запрос – QUERY FOR ID 2, который выполнялся
на порядок дольше, чем остальные запросы. На вход приложению
подается файл с логами (от нескольких байт, до десятков гигабайт) и
параметр отклонения, например, 3 seconds или 3 minutes. Если
параметр не задан, то программа самостоятельно должна выявить
аномальные запросы (разработчик сам определяет алгоритм).
Необходимо отображать прогресс работы (сколько обработано логов,
сколько осталось обработать логов). Пользовательский интерфейс для
программы необязателен.
