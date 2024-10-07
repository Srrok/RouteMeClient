## RouteMe: Планировщик идеального путешествия

**RouteMe** - это приложение с открытым исходным кодом, которое поможет вам создать идеальный маршрут для вашего следующего путешествия.

**🚀 Особенности:**

* **Персонализация маршрута:**  Выбирайте теги, которые вам интересны, будь то история, искусство, еда, природа или что-то другое.
* **Генерация оптимального маршрута:**  Приложение использует алгоритмы, которые оптимизируют ваш маршрут, чтобы вы могли увидеть максимум интересных мест за минимальное время.
* **Интеграция с картами:**  Просматривайте свой маршрут на карте и получайте подробную информацию о каждом месте.
* **Простой и интуитивно понятный интерфейс:**  Создавайте и редактируйте маршруты без лишних сложностей.
* **Полная свобода:**  Используйте RouteMe бесплатно, без рекламы и ограничений.

**🔥 Принцип работы клиента**
Приложение имеет два активных экрана: загрузчик и главный экран соответственно. Загрузчик будет ожидать загрузки компонента WebView на главном экране,
после чего переведёт пользователя на страницу с активным WebView-ом. WebView компонент имеет все необходимые разрешения для работы с телефоном клиента,
дабы обеспечить правильную работу Web приложения. У компонента есть собственный UserAgent, по которому сайт будет определять, откуда именно производится
вход в приложение, таким образом подбирая формат страницы и контент на ней под нужды пользователя: презентация проекта в Web формате или активное приложение

**💻 Как начать использование:**
1. **Перейдите во вкладку Releases** [https://github.com/Srrok/RouteMeClient/releases]
2. **Установите крайнюю версию приложения**
3. **В проводнике найдите установленный .apk файл**
4. **Нажмите на него для установки**
5. **В приложении авторизуйтесь через сервисы Sыrka** [https://srrok.ru]

**🛠 Для разработчиков:**
1. **Загрузите исходный код клиента:** [https://github.com/Srrok/RouteMeClient]
2. **Загрузите исходный WEB клиента:** [https://github.com/Srrok/RouteMe]
3. **Откройте проект в VS Code и Android Studio соответственно**

**🔐 Обновление репозитория**
```Bash
npm run git
```
С обновлением репозитория происходит автоматическое обновление
приложения у клиента, закреплённого сайта, а также обновление
Prisma схемы базы данных

**🙋‍♂️ Команда**
* [Александр Савунов - FullStack Программист (Sыrok)](https://t.me/srro4k)
* [Константин Сокольников - Backend Программист](https://t.me/Kostyan_Metall_bank)
* [Максим Гонохов - Научный руководитель](https://vk.com/miksus)