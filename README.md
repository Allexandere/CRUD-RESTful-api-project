**Эндпоинты**
-
Все эндпоинты начинаются с **/api**.

* **/students**

  Без параметров возвращает всех студентов, иначе возвращает результат соответствующих запросов к БД.

* **Method:**

  `GET`
  
*  **URL Params**

   **Optional:**
 
   `name=[string]`
   `lastName=[string]`
   `minAge=[integer]`
   `maxAge=[integer]`
   `minRating=[double]`
   `maxRating=[double]`
   `mail=[string]`

* **Success Response:**

  Возвращает массив студентов в соответствии с указанными параметрами.

  * **Code:** 200 <br />
    **Content:** `[]`
    
  * **Code:** 200 <br />
    **Content:**  `[
    {"id": 1,
    "firstName": "Ivan",
    "lastName": "Ivanov",
    "age": 19,
    "rating": 8.21,
    "mail": "ivan@mail.ru"}
    ]` 
 
* **Error Response:**

  Если один из параметров указан неверно возвращает ошибку 400.

  * **Code:** 400 <br />
    **Content:** `[{"status": 400,
    "error": "Bad Request"}]`

* **Sample Call:**

  `api/students?minRating=8&name=v&mail=@mail.ru`

* **Notes:**

 Если параметр пустой, то он игнорируется.
 
 ---
 
* **/get_student**

  Без параметров возвращает всех студентов, иначе возвращает результат соответствующих запросов к БД.

* **Method:**

  `GET`
  
* **URL Params**

  **Required.**
  
   'id=[string]'

* **Success Response:**

  Возвращает студента с указанным id.
    
  * **Code:** 200 <br />
    **Content:**  `[
    {"id": 1,
    "firstName": "Ivan",
    "lastName": "Ivanov",
    "age": 19,
    "rating": 8.21,
    "mail": "ivan@mail.ru"}
    ]` 
 
* **Error Response:**

  Если id указан неверно возвращает ошибку 400.

  * **Code:** 400 <br />
    **Content:** `{"Invalid id"}`
    
  Если студента с указанным id нет возвращает ошибку 404.

  * **Code:** 404 <br />
    **Content:** `{"No student with such id"}`

* **Sample Call:**

  `api/get_student?id=1`
 
 ---
 
* **/create_student**

  Без параметров возвращает всех студентов, иначе возвращает результат соответствующих запросов к БД.

* **Method:**

  `POST`
  
* **Data Params**

  `"{firstName": "Ivan",
    "lastName": "Ivanov",
    "age": 19,
    "rating": 8.21,
    "mail": "ivan@mail.ru"}` 

* **Success Response:**

  Возвращает созданный объект-студента с присвоенным id.

  * **Code:** 200 <br />
    **Content:**  `{"id": 1,
    "firstName": "Ivan",
    "lastName": "Ivanov",
    "age": 19,
    "rating": 8.21,
    "mail": "ivan@mail.ru"}` 
 
* **Error Response:**

  Если один или несколько из параметров указаны неверно возвращает ошибку 400.<br/>

  * **Code:** 400 <br />
    **Content:** `["Rating must be in [0, 10] and can't be null.
  First name size must be in [1, 100] and can't be null. 
  Last name size must be in [1, 100] and can't be null.
  Age must be in [1, 120] and can't be null.
  Student with this mail already exists."]`

* **Sample Call:**

  `api/create_student'
  
  `body = {
    "firstName": "Ivan",
    "lastName": "Ivanov",
    "age": 19,
    "rating": 8.21,
    "mail": "ivan@mail.ru"}`

* **Notes:**

 Ограничения на параметры описаны в примере с ошибкой.
 
 ---
 
 **TO BE CONTINUED**
