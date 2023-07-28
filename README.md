Имя ученика: Денис; </br>
Название проекта: RecipeApp; </br>
Дата создания: 05.02.2023; </br>
  Необходимо создать сервис, который будет хранить рецепты и возвращать рецепты по его идентификатору.
Храниться рецепты должны в карте в формате <номер рецепта, рецепт>.

  Поля класса рецепта должны содержать:
Название в формате строки;
Время приготовления в минутах в формате целого положительного числа;
Ингредиенты в формате списка объектов;
Шаги приготовления в формате списка строк.

  Поля класса ингредиента должны содержать:
Название в формате строки;
Количество ингредиентов в формате целого положительного числа;
Единица измерения в формате строки.

  В сервисе должны быть реализованы методы:
1. Добавления нового рецепта.
В метод передается заполненный объект класса рецепта, который сохраняется в карте и получает свой порядковый номер.
2. Получение рецепта.
В метод передается порядковый номер рецепта, на выходе мы получаем из карты нужный объект.
