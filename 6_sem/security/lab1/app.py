import os

import psutil
import subprocess
import platform

from flask import Flask, render_template, redirect, url_for, request
from dotenv import load_dotenv

app = Flask(__name__)

load_dotenv()
sudo_pass = os.getenv('PASS')


def get_disk_usage():
    disk_usage = psutil.disk_usage('/')
    return {
        'Total': '%d Gb' % (disk_usage.total // (2 ** 30)),
        'Used': '%d Gb' % (disk_usage.used // (2 ** 30)),
        'Free': '%d Gb' % (disk_usage.free // (2 ** 30))
    }


def get_cpu():
    # Get CPU information
    cpu_freq = psutil.cpu_freq().max / 1000.0

    output_model = subprocess.check_output('cat /proc/cpuinfo | grep "model name" | uniq | cut -d ":" -f 2', shell=True)
    output_cores = subprocess.check_output('cat /proc/cpuinfo | grep processor | wc -l', shell=True)

    cpu_model = output_model.decode('utf-8').strip()
    cpu_cores = output_cores.decode('utf-8').strip()

    return {
        'Model': cpu_model,
        'Cores': cpu_cores,
        'Frequency': '%s GHz' % cpu_freq,
        'Current load': '%s%%' % psutil.cpu_percent()
    }


def get_ram():
    # Get RAM information
    ram_info = psutil.virtual_memory()

    return {
        'Total': '%.2f GB' % (ram_info.total / (1024 ** 3)),
        'Available': '%.2f GB' % (ram_info.available / (1024 ** 3)),
        'Used': '%.2f GB' % (ram_info.used / (1024 ** 3)),
        'Percentage': '%.2f%%' % ram_info.percent
    }


def get_swap():
    # Get swap memory information
    swap_info = psutil.swap_memory()

    return {
        'Total': '%.2f GB' % (swap_info.total / (1024 ** 3)),
        'Available': '%.2f GB' % (swap_info.free / (1024 ** 3)),
        'Used': '%.2f GB' % (swap_info.used / (1024 ** 3)),
        'Swap': '%.2f%%' % swap_info.percent
    }


def system_update():
    output = 'Error while checking updates.'
    if 'MANJARO' in platform.platform():
        try:
            output = subprocess.check_output(f'echo {sudo_pass} | sudo -S pacman -Syu', shell=True)
        except subprocess.CalledProcessError:
            output = b'Error checking updates'

    if 'Ubuntu' in platform.platform():
        try:
            output = subprocess.check_output(f'echo {sudo_pass} | sudo -S apt upgrade -s', shell=True)
        except subprocess.CalledProcessError:
            output = b'Error checking updates'

    if b'packages can be upgraded' in output:
        output_str = 'There are updates available for this system'
    elif b'Error' in output:
        output_str = 'Error getting update information'
    else:
        output_str = 'Your system is up to date.'

    return {
        'Platform': platform.platform(),
        'Info': output_str
    }


def firewall():
    output = subprocess.check_output(f'echo {sudo_pass} | sudo -S iptables -L -v -n', shell=True)
    output_str = output.decode('utf-8')
    res = output_str.split('Chain')
    return {
        'Input': res[1],
        'Forward': res[2],
        'Output': res[3]
    }


def custom_command(command, as_root):
    if as_root is None:
        try:
            output = subprocess.check_output(f'{command}')
        except Exception:
            output = b'Error executing command'
    else:
        try:
            output = subprocess.check_output(f'echo {sudo_pass} | sudo -S {command}')
        except Exception:
            output = b'Error executing command as ROOT'

    output_str = output.decode('utf-8')
    return {
        command: output_str
    }


names = ['Disk Usage', 'CPU', 'RAM', 'Swap', 'System', 'Firewall']
values = [get_disk_usage(), get_cpu(), get_ram(), get_swap(), system_update(), firewall()]


@app.route('/')
def index():
    info = {names[i]: values[i] for i in range(len(names))}
    return render_template('index.html', names=names, info=info), 200


@app.route('/custom', methods=['GET', 'POST'])
def custom():
    if request.method == 'GET':
        return render_template('command.html')
    if request.method == 'POST':
        global names
        global values
        print(request.form)
        com = request.form['inputField']
        checkbox = None
        try:
            checkbox = request.form['checkbox']
        except Exception:
            checkbox = None
        res = custom_command(com, checkbox)
        names = ['Custom check']
        values = [res]
        print(names, values)
        return redirect(url_for('index'))


@app.route('/full')
def full():
    global names
    global values
    names = ['Disk Usage', 'CPU', 'RAM', 'Swap', 'System', 'Firewall']
    values = [get_disk_usage(), get_cpu(), get_ram(), get_swap(), system_update(), firewall()]
    return redirect(url_for('index'))


@app.route('/system')
def system():
    global names
    global values
    names = ['System']
    values = [system_update()]
    return redirect(url_for('index'))


@app.route('/firewall')
def firewall_info():
    global names
    global values
    names = ['Firewall']
    values = [firewall()]
    return redirect(url_for('index'))


@app.route('/hardware')
def hardware():
    global names
    global values
    names = ['Disk Usage', 'CPU', 'RAM', 'Swap']
    values = [get_disk_usage(), get_cpu(), get_ram(), get_swap()]
    return redirect(url_for('index'))


if __name__ == '__main__':
    app.run(debug=True)
