<h1 align="center">Приложение для отображения Gif-изображений</h1>

Android-приложение для отображения gif-изображений с сайта [developerslife.ru ![developerslife.ru](https://developerslife.ru/images/fav.png)](https://developerslife.ru/)
<br>Приложение будет обладать следующим минимальным функционалом:
1. На главном экране приложения отображается gif-изображение вместе с его 
описанием, полученным с сайта [developerslife.ru ![developerslife.ru](https://developerslife.ru/images/fav.png)](https://developerslife.ru/)
2. Под изображением располагаются две кнопки:
- Одна (кнопка «следующий») загружает следующий рандомный пост (вызывая метод API).
- Вторая — позволяет вернуться к предыдущему посту, который мы сохранили в кэш после загрузки.
- Получается такая структура: 
(изначально кнопка назад не активна, но уже есть какой-то пост, загруженный при старте) нажимаем на «следующий» →
появляется пост и кнопка назад становится активна → 
нажимаем на «следующий» ещё раз → 
появляется новый пост и обе кнопки активны →
нажимаем назад и попадаем на один пост назад (обе кнопки активны) →
нажимаем ещё раз и попадаем на первую картинку (кнопка назад стала не активна снова).
<br>Теперь если кликнуть на «следующий» мы сначала должны пройти те посты, которые у нас были загружены, а потом как они кончатся — загружать новые.
3. Ответы от API должны быть закешированы хотя бы на время сессии для
реализации переходов «назад».
4. Должны быть предусмотрены различные состояния загрузки данных: ошибка
загрузки, загрузка и успешная загрузка.
