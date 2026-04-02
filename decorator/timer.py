import time
from functools import wraps
from typing import Callable, Any
from datetime import datetime
from loguru import logger

def timer(func: Callable = None, *, prefix: str = '', use_ms: bool = False):
    """
    计时装饰器

    Args:
        func: 被装饰的函数
        prefix: 输出信息前缀
        use_ms: 是否使用毫秒显示

    Example:
        @timer
        def my_func(): pass

        @timer(prefix='MyFunc: ', use_ms=True)
        def my_func(): pass
    """

    def decorator(fun: Callable) -> Callable:
        @wraps(fun)
        def wrapper(*args, **kwargs) -> Any:
            start_time = datetime.now()
            start_perf = time.perf_counter()

            result = fun(*args, **kwargs)

            end_time = datetime.now()
            end_perf = time.perf_counter()
            elapsed = end_perf - start_perf

            start_str = start_time.strftime('%H:%M:%S.') + f'{start_time.microsecond // 1000:03d}'
            end_str = end_time.strftime('%H:%M:%S.') + f'{end_time.microsecond // 1000:03d}'

            logger.info(f"开始时间:{start_str}")
            logger.info(f"结束时间:{end_str}")

            if use_ms:
                logger.info(f"执行时间： {elapsed * 1000:.2f}ms")
            else:
                logger.info(f"执行时间: {elapsed:.4f}s")

            return result

        return wrapper
    return decorator(func)
