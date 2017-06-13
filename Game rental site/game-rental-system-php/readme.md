
Install XAMPP
 * [Window](https://www.apachefriends.org/index.html)
 
Install composer
	* [Window](https://getcomposer.org/Composer-Setup.exe)
    
Install depenecies
	* Open cmd
    * Move to project folder
    * Type
    ```
    	composer install
    ```
    * Wait to complete

Run app
	* Open cmd
    * Move to project folder
    * Type
    ```
        php initdb
        
        php -S localhost:8000 -t ./public/
    ```
    * Open browser with address http://localhost:8000