from flask import Flask
import endpoints
import subprocess

app = Flask(__name__)
app.register_blueprint(endpoints.ai_content_blueprint, url_prefix='/content')
app.register_blueprint(endpoints.ai_board_blueprint, url_prefix='/board')

label = subprocess.check_output(["git", "describe", "--always"]).strip().decode()

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000)
