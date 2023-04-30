import hashlib
import os
import zipfile
import subprocess
import PIL

from PIL import Image


def zip_folder(path: str) -> str:
    """
    Method to create .zip archive of a folder
    :param path: Path to folder
    :return: Result
    """
    try:
        with zipfile.ZipFile(path + '.zip', "w", zipfile.ZIP_DEFLATED) as zip_file:
            for root, dirs, files in os.walk(path):
                for file in files:
                    file_path = os.path.join(root, file)
                    zip_file.write(file_path, os.path.relpath(file_path, path))
    except PermissionError:
        return 'Required SUDO privileges'

    return f'Created zip archive { path }.zip'


def zip_folder_sudo(path, sudo_pass):
    command = f'echo "{sudo_pass}" | sudo -S zip -r {path + ".zip"} {path}'
    subprocess.run(command, shell=True)
    return f'Created zip archive { path }.zip with SUDO privileges'


def count_hash(file_path: str) -> str:
    """
    Method to count sha-256 hash of a file
    :param file_path: Path to file
    :return: Result
    """

    hash_sha = hashlib.sha256()
    with open(file_path, "rb") as file:
        while True:
            data = file.read(65536)
            if not data:
                break
            hash_sha.update(data)
    hash_res = hash_sha.hexdigest()

    try:
        with open(file_path + '.hash', 'w') as file:
            file.write(hash_res)
    except PermissionError:
        return 'Required SUDO privileges'

    return hash_res


def count_hash_sudo(file_path, sudo_pass):
    hash_sha = hashlib.sha256()
    with open(file_path, "rb") as file:
        while True:
            data = file.read(65536)
            if not data:
                break
            hash_sha.update(data)
    hash_res = hash_sha.hexdigest()

    command = f'echo {sudo_pass} | sudo -S -i sh -c "echo {hash_res} > {file_path + ".hash"}"'
    # print(command)
    subprocess.run(command, shell=True, encoding='utf-8')

    return hash_res


def convert_png(path: str) -> str:
    """
    Method to convert graphical file to a PNG format
    :param path: Path to file
    :return: Result
    """
    convert_path = path.split('.', 1)
    path_res = convert_path[0] + '.png'
    # print(convert_path)

    try:
        with Image.open(path) as img:
            img.save(path_res, "PNG")
    except PermissionError:
        return 'Required SUDO privileges'
    except PIL.UnidentifiedImageError:
        return 'Not an image!'

    return 'Successfully saved file as .png'


def convert_png_sudo(path, sudo_pass):
    convert_path = path.split('.', 1)
    path_res = convert_path[0] + '.png'
    command = f'echo {sudo_pass} | sudo -S cp {path} {path_res}'
    subprocess.run(command, shell=True)
    return 'Successfully saved file as .png with SUDO privileges'
