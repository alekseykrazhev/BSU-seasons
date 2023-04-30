import os

from flask import Flask, render_template, request, redirect, url_for
from controller import zip_folder, count_hash, convert_png, zip_folder_sudo, convert_png_sudo, count_hash_sudo

app = Flask(__name__)

path = ''


@app.route('/', methods=['GET', 'POST'])
def index():
    global path
    result = ''
    if request.method == 'GET':
        return render_template('index.html', result=result), 200

    if request.method == 'POST':
        try:
            path = request.form['inputField']
            print(path)
        except Exception:
            return 'Could not get valid path to folder'

        if path == '':
            result = 'Please, enter path'
        else:
            result = zip_folder(path)
            if result == 'Required SUDO privileges':
                return redirect(url_for('sudo_zip'))

        return render_template('index.html', result=result), 200

    return render_template('index.html', result=result), 200


@app.route('/hash', methods=["GET", "POST"])
def hash_route():
    global path
    result = ''
    if request.method == 'GET':
        return render_template('hash.html', result=result), 200

    if request.method == 'POST':
        path = request.form['inputField']
        print(path)

        if path == '':
            result = 'Please, enter path to a file'
        else:
            result = count_hash(path)
            if result == 'Required SUDO privileges':
                return redirect(url_for('sudo_hash'))

        return render_template('hash.html', result=result), 200

    return render_template('hash.html', result=result), 200


@app.route('/png', methods=['GET', 'POST'])
def png_route():
    result = ''
    if request.method == 'GET':
        return render_template('png.html', result=result), 200
    if request.method == 'POST':
        path = request.form['inputField']
        print(path)

        if path == '':
            result = 'Please, enter path'
        else:
            result = convert_png(path)
            if result == 'Required SUDO privileges':
                return redirect(url_for('sudo_png'))

        return render_template('png.html', result=result), 200

    return render_template('png.html', result=result), 200


@app.route('/sudo_zip', methods=['GET', 'POST'])
def sudo_zip():
    if request.method == 'GET':
        return render_template('sudo.html'), 200
    elif request.method == 'POST':
        sudo_pass = request.form['sudo-input']
        zip_folder_sudo(path, sudo_pass)

        return redirect(url_for('index'))

    return render_template('sudo.html'), 200


@app.route('/sudo_hash', methods=['GET', 'POST'])
def sudo_hash():
    if request.method == 'GET':
        return render_template('sudo.html'), 200
    elif request.method == 'POST':
        sudo_pass = request.form['sudo-input']
        count_hash_sudo(path, sudo_pass)

        return redirect(url_for('hash_route'))

    return render_template('sudo.html'), 200


@app.route('/sudo_png', methods=['GET', 'POST'])
def sudo_png():
    if request.method == 'GET':
        return render_template('sudo.html'), 200
    elif request.method == 'POST':
        sudo_pass = request.form['sudo-input']
        convert_png_sudo(path, sudo_pass)

        return redirect(url_for('hash_route'))

    return render_template('sudo.html'), 200


if __name__ == '__main__':
    if os.geteuid() == 0:
        print('App is running as SUDO, stopping...')
    else:
        app.run()
