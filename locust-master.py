from locust import User, task

class LocustTask(User):
    @task(1)
    def Locust(self):
        pass


class Locust(User):
    task_set=LocustTask