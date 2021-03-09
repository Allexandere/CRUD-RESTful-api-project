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

  Безопасный метод, ошибок не вызывает.

* **Sample Call:**

  `api/students?minRating=8&name=v&mail=@mail.ru`

* **Notes:**

 Если параметр пустой, то он игнорируется.
 
 ---
 
 **TO BE CONTINUED**
