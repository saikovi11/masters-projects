
Install python 2.7.x
 * [Window](https://www.python.org/ftp/python/2.7.11/python-2.7.11.amd64.msi)
 
Install python pip
	* [Window](https://pip.pypa.io/en/stable/installing/)
    
Install depenecies
	* Open cmd
    * Move to project folder
    * Type
    ```
    	pip install -r requirements.txt
    ```
    * Wait to complete

Run app
	* Open cmd
    * Move to project folder
    * Type
    ```
    	export FLASK_APP=rental_game.py
        python -m flask initdb
        python -m flask migrationdb
        python -m flask run
    ```
    * Open browser with address http://localhost:5000
