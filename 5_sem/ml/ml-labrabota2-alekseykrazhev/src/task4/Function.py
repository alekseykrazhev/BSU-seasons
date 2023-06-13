
def check_triangle(a: int, b: int, c: int) -> bool:
    """
    Function to check whether triangle exists
    :param a: first side
    :param b: second side
    :param c: third side
    :return: True - if exists, False - otherwise
    """
    if a <= 0 or b <= 0 or c <= 0:
        return False

    if a >= b + c or b >= a + c or c >= a + b:
        return False

    return True
