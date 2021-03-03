# ProgressFeatureSample

[Article on Habr (in Russian)](https://habr.com/ru/company/surfstudio/blog/521416/ "Статья на Habr")

In mobile applications you can meet such features as filling out questionnaires and submitting applications. Usually, they consist of several sequential screens, but in fact, they are all filling one large object with data. The data filled in the previous steps may be necessary for the next steps, depending on the parameters selected by the user, the composition of the steps may vary. For such long sequences of screens, your application might want to save the data “to a draft” so that the user can return to the process later. This repository demonstrates convenient practices for organizing the work of such features.

Let's imagine that we are developing a feature that allows users to respond to a vacancy they like. But the user needs to fill out a short questionnaire to respond. When the user fills in everything, we want to send a request to the server.

Our questionnaire will consist of the following steps:
* Step 1 - filling in the full name, type of education, work experience
* Step 2 (optional) - filling out the place of study
* Step 3 - filling out a job or writing an essay about yourself
* Step 4 - choosing the reasons why you are interested in this vacancy

<img src="https://i.ibb.co/dGvbkLY/shagi-polny-flou.jpg"  />

The questionnaire will change depending on whether the user has education and work experience. If there is no education, we will exclude the step of filling the place of study, and if there is no work experience, instead of the step with filling the place of work, we will ask the user to write a little about himself.

The described approach answers the questions:
* How to make the feature scenario flexible and able to easily add and remove steps from the scenario
* How to ensure that when you open a step, the data it needs is already filled in
* How to ensure data aggregation into a common model for sending to the server after the last step
* How to save the application in “draft” so that the user can interrupt filling and return to it later

The screen architecture is based on an open framework [SurfAndroidStandard](https://github.com/surfstudio/SurfAndroidStandard)

## Basic entities

The pattern consists of:
* A set of models for describing a step, inputs and outputs
* [Scenario] - an entity describing which steps (screens) the user needs to go through
* Interactor [ProgressInteractor] - a class responsible for storing information about the current active step, aggregating the filled information after the completion of each step and providing input data for new step start
* Draft [ApplicationDraft] - a class responsible for storing filled information

=================================================================================================================================

[Статья на Habr](https://habr.com/ru/company/surfstudio/blog/521416/ "Статья на Habr")

В мобильных приложения встречаются такие фичи, как заполнение анкет и подача заявок. Обычно они состоят из нескольких последовательных экранов, но по факту являются заполнением одного большого объекта с данными. Данные, заполненные на предыдущих шагах, могут быть необходимы для работы следующих, в зависимости от выбранных пользователем параметров, состав шагов может меняться. Для таких длинных последовательностей экранов в приложении может быть предусмотрено сохранение данных “в черновик” для возвращения к процессу позже. В этом репозитории продемонстрированы удобные практики для организации работы именно таких фич.

Для примера представим, что мы разрабатываем фичу, которая позволит откликнуться на понравившуюся вакансию и для этого пользователю нужно заполнить небольшую анкету. Когда пользователь всё заполнит, мы хотим отправить заявку на сервер. 
Наша анкета будет состоять из следующих шагов:
* Шаг 1 - заполнение ФИО, типа образования, наличия опыта работы
* Шаг 2 (опциональный) - заполнение места учебы
* Шаг 3  - заполнение места работы или написание эссе о себе
* Шаг 4 - выбор причин, почему заинтересовала данная вакансия

<img src="https://i.ibb.co/dGvbkLY/shagi-polny-flou.jpg"  />

Анкета будет меняться в зависимости от того, есть ли у пользователя образование и опыт работы. Если образования нет - исключим шаг, заполнения места учебы, а если нет опыта работы, вместо шага с заполнением мест работы попросим пользователя написать немного о себе.

Описаный подход отвечает на вопросы:
* Как сделать сценарий фичи гибким и иметь возможность легко добавлять и убирать шаги из сценария
* Как гарантировать, что при открытии шага нужные ему данные уже будут заполнены
* Как обеспечить агрегацию данных в общую модель для передачи на сервер после последнего шага
* Как сохранить заявку в “черновик”, чтобы пользователь мог прервать заполнение и вернуться к нему позже

Архитектура экранов основана на открытом фреймворке [SurfAndroidStandard](https://github.com/surfstudio/SurfAndroidStandard)

Пример продолжения заполнения из черновика:

<a href="https://imgbb.com/"><img src="https://i.ibb.co/S36NzRp/ddpbnugu6cc6uxbk-oia2gz-h3w.gif" alt="ddpbnugu6cc6uxbk-oia2gz-h3w" border="0"></a>
