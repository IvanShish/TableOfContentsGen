# TableOfContentsGen

Условие задания:

Задача 1
Вам нужно написать генератор оглавления для markdown файлов.

Это должна быть command-line тула на Java, которая принимает на вход путь к markdown файлу, добавляет к нему в начало оглавление и выводит результат в standard output.

Нельзя пользоваться библиотеками для генерации и парсинга markdown.

```
Input:

# My Project
## Idea
content
## Implementation
### Step 1
content
### Step 2
content
Output:

1. [My Project](#my-project)
    1. [Idea](#idea)
    2. [Implementation](#implementation)
        1. [Step 1](#step-1)
        2. [Step 2](#step-2)

# My Project
## Idea
content
## Implementation
### Step 1
content
### Step 2
content
```
