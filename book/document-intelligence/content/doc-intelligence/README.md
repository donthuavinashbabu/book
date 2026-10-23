## Install UV
* Execute below command
```
powershell -ExecutionPolicy ByPass -c "irm https://astral.sh/uv/install.ps1 | iex"
```
* Create virtual environment
```
uv venv
```
* Activate it in this PowerShell window, allow scripts for the current session only
```
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
.\.venv\Scripts\activate
```
CMD instead of PowerShell
```
.venv\Scripts\activate.bat
```
* Permanent (current user)
```
Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned
.\.venv\Scripts\activate.ps1
```
* Download the dependencies from the requirements.txt file
```
uv pip install -r requirements.txt
```
* Run the application
```
uv run main.py
python main.py
```

* Execute below command to install the dependencies
```
uv sync
```
* Execute below command to run the application
```
uv run app
```