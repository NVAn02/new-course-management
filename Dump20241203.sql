-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: course-db
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `lesson_content` longtext,
  `price` int DEFAULT NULL,
  `short_description` longtext,
  `created` datetime(6) DEFAULT NULL,
  `description` longtext,
  `image` varchar(255) DEFAULT NULL,
  `requirement` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Khóa học Java Spring Boot','Có khả năng xây dựng một ứng dụng web thực tế hoàn chỉnh từ đầu đến cuối, kết hợp giữa phần back - end với Java Springboot API và phần front - end với Angular\nHiểu rõ về các công nghệ an toàn liên quan đến việc xây dựng RESTful API như JWT, Spring Security và OAuth 2.0, phương pháp để tích hợp vào các dự án thực tế\nCó khả năng xây dựng một RESTful API với Java Springboot từ đầu đến cuối, bao gồm các tính năng cơ bản như đăng nhập, đăng ký và quản lý sản phẩm và đơn hàng\nThành thạo sử dụng Angular để tạo giao diện người dùng cho ứng dụng web, kết hợp với RESTful API đã xây dựng để hiển thị thông tin sản phẩm và quản lý đơn hàng',299,'Xây dựng ứng dụng bán hàng với Java Springboot API và Angular','2024-03-20 00:00:00.000000','Khóa học \"Thực chiến, xây dựng ứng dụng bán hàng với Java Springboot API và Angular\" sẽ giúp bạn học cách tạo một ứng dụng web thực tế hoàn chỉnh từ đầu đến cuối, bao gồm cả phía back-end và front-end.\n\nBạn sẽ học cách sử dụng Java Springboot để xây dựng một RESTful API, cung cấp các tính năng cần thiết cho ứng dụng bánhàng như đăng nhập, đăng ký người dùng, quản lý sản phẩm và đơn hàng. Ngoài ra, bạn cũng sẽ được hướng dẫn cách áp dụngcác công nghệ an toàn trong việc xây dựng API như JWT, Spring Security và OAuth 2.0.\n\nBên cạnh đó, khóa học cũng sẽ giới thiệu về Angular - một framework front-end phổ biến để xây dựng các ứng dụng web động. Bạn sẽ học cách sử dụng Angular để tạo giao diện người dùng cho ứng dụng bán hàng của mình, kết hợp với API đã xây dựng để hiển thị thông tin sản phẩm và quản lý đơn hàng.\n\nSau khi hoàn thành khóa học này, bạn sẽ có kiến thức và kỹ năng để xây dựng một ứng dụng web hoàn chỉnh với JavaSpringboot API và Angular, đáp ứng được các yêu cầu của một ứng dụng bán hàng thực tế.','https://akdemy.net/wp-content/uploads/2023/04/Spring-Boot-Framework-400x300.png',' - Basic Java knowledge is required\n - Basic HTML knowledge is helpful'),(2,'AWS Cloud for beginner','FULLY UPDATED FOR SAA-C03: Pass the AWS Certified Solutions Architect Associate Certification\nFull Practice Exam with Explanations included!\nAll 800+ slides available as downloadable PDF\nPerform Real-World Solution Architecture on AWS\nLearn the AWS Fundamentals (EC2, ELB, ASG, RDS, ElastiCache, S3)\nLearn the Serverless Fundamentals (Lambda, DynamoDB, Cognito, API Gateway)',249,'AWS Cloud for beginner (Vietnamese)AWS Cloud cho người mới bắt đầu (Tiếng Việt)','2024-03-20 00:00:00.000000','Complete Guide to Mastering the Fundamentals of AWS Cloud Computing- No Prior Experience Required\nThis course is your starting point to become an AWS Certified Cloud Practitioner!\n\nWhy should YOU become an AWS Certified Cloud Practitioner?\nWith 60% of the cloud computing job postings centered on AWS, there is an overwhelmingly rising demand for cloud-based solutions, especially in the space of e-commerce.\nWith that, the requirement for professionals who can navigate the intricate world of AWS is increasing at a terrific pace!\n\nBut as a beginner, it can be challenging to learn AWS/ cloud computing, especially with…\n1. Overwhelming Variety of Services: AWS offers a vast array of services. The sheer number of options can be overwhelming for a beginner like you. Understanding what each service does and when to use it can be a head-scratcher.\n2. Technical Jargon: Cloud computing and AWS come with their own set of terminology. Terms like Amazon EC2, S3, VPC, AWS Lambda, etc., can be confusing for someone new.\n3. Practical Hands-on Experience: While theoretical fundamentals are essential, beginners often struggle to find opportunities to practice hands-on, which is crucial for truly getting the hang of cloud services.\n\nYou\'re not alone if you\'ve ever felt overwhelmed, lost, or even fearful about making mistakes that could cost you on AWS.\nWithout clear guidance, diving into AWS can lead to unnecessary costs, improperly configured services, or even potential data breaches.\nWhether you’re managing the platform for yourself or your clients, any costly mistake can take a huge toll on your career as an AWS Certified Cloud Practitioner.','https://www.greenmarimba.com/files/sites/229/2020/01/aws-cloud.png',' - Know the basics of IT\n - No AWS Cloud experience is necessary, we\'ll use the AWS Free Tier\n - Windows / Linux / Mac OS X Machine'),(3,'React Ultimate - React.JS Cơ Bản','Become an advanced, confident, and modern React developer from scratch\nBuild 8+ beautiful projects, including one HUGE professional real-world app\nBecome job-ready by working with libraries and tools used in professional projects\nJoin my other 1,800,000+ happy students on this journey\nThink like a senior React engineer with advanced design patterns\nReact fundamentals: components, JSX, props, events, state, forms',299,'React Ultimate - React.JS Cơ Bản Từ Z Đến A Cho Beginners Hiểu và Làm Chủ React','2024-03-20 00:00:00.000000','This bestselling course has turned more than 800,000 students into ReactJS developers (more than any other React course on Udemy)!\n-\nUpdated January 2024:\nAdded brand-new content (~3h) on the NextJS App Router\nAdded a brand-new \"Best Practices & Patterns\" section\nBIG COURSE UPDATE October 2023:\nUpdated the entire first half of the course (re-recorded lectures, other half was updated at beginning of year already)!\nImproved explanations & demo projects. Course now covers even more key concepts & best practices.\n\nUpdated August 2023:\nAdded brand-new section on React Query (Tanstack Query)\nAdded brand-new section on animating React apps with Framer Motion\n\nUpdated June 2023:\nAdd two new practice projects\nAdded more coding exercises\nAdded brand-new JS Refresher section\nTons of minor improvements & fixes\nThis course is completely up-to-date with the very latest version of React with all the core, modern features you need to know & teaches you React in a practical, hands-on way!\n\nUpdated February 2023:\nCompletely re-recorded majority of second half of course (incl. routing, authentication)\n\n\n\nA Course For Busy Customers & Business Professionals!\nThis course also comes with two paths which you can take: The \"complete\" path (full >40h course) and the \"summary\" (fast-track) path (~4h summary module) - you can choose the path that best fits your time requirements! ','https://thuanbui.me/wp-content/uploads/2021/08/react-js-1200x675.png',' - NO React experience necessary! I take you from beginner to expert!\n - Any computer and OS will work — Windows, macOS or Linux\n - Basic understanding of JavaScript is required (this course contains a quick JavaScript review section)'),(4,'Khóa học Python cho người mới','You will master the Python programming language by building 100 unique projects over 100 days.\nYou will learn automation, game, app and web development, data science and machine learning all using Python.\nYou will learn Selenium, Beautiful Soup, Request, Flask, Pandas, NumPy, Scikit Learn, Plotly, and Matplotlib.\nYou will be able to program in Python professionally\nCreate a portfolio of 100 Python projects to apply for developer jobs\nBe able to build fully fledged websites and web apps with Python',299,'Chia sẻ với các bạn một cách tổng quan và dễ hình dung nhất về Python','2024-03-20 00:00:00.000000','t\'s time to become a modern and complete Python developer! Join a live online community of over 900,000+ developers and a course taught by an industry expert that has actually worked both in Silicon Valley and Toronto. Graduates of Andrei’s courses are now working at Google, Tesla, Amazon, Apple, IBM, JP Morgan, Meta, + other top tech companies.\n\nLearn Python from scratch, get hired, and have fun along the way with the most modern, up-to-date Python course on Udemy (we use the latest version of Python). This course is focused on efficiency: never spend time on confusing, out of date, incomplete Python tutorials anymore.\n\nThis comprehensive and project based course will introduce you to all of the modern skills of a Python developer (Python 3) and along the way, we will build over 12 real world projects to add to your portfolio (You will get access to all the the code from the 12+ projects we build, so that you can put them on your portfolio right away)!\n\nThe curriculum is going to be very hands on as we walk you from start to finish of becoming a professional Python developer. We will start from the very beginning by teaching you Python basics and programming fundamentals, and then going into advanced topics and different career fields in Python so you can get real life practice and be ready for the real world.','https://akdemy.net/wp-content/uploads/2023/07/python-co-ban-1-300x210.jpg',' - No programming experience needed - I\'ll teach you everything you need to know \n - A Mac or PC computer with access to the internet \n - No paid software required - I\'ll teach you how to use PyCharm, Jupyter Notebooks and Google Colab\n - I\'ll walk you through, step-by-step how to get all the software installed and set up'),(5,'NextJS 14-ReactJS-Typescript','Nắm vững các kiến thức của ReactJS\nNắm vững và thành thạo sử dụng NextJS 14 - NextAuth\nThành thạo sử dụng Redux (Redux thunk, Redux toolkit)\nSử dụng thư viện Material UI để xây dựng giao diện nhanh hơn\nBiết cách tối ưu code và tăng performance\nBiết cách phân quyền cho hệ thống trong thực tế',499,'Khóa học Nextjs 14, ReactJS, Typescript từ cơ bản đến thực chiến cho người mới bắt đầu','2024-03-20 00:00:00.000000','Update December 2023:\n - Added a HUGE new section (incl. a completely new demo project) that covers NextJS 14 & the \"App Router\" in-depth!\n - Covers React Server Components, Server Actions & more\n - Covers file upload & storage on S3\n - Completely updated the \"React Refresher\" section\n - No prior NextJS knowledge is required - I\'ll introduce you to what the \"App Router\" is in this course!\n\nJoin this bestselling NextJS course and learn how to build highly dynamic, super fast and SEO-ready React apps with React & NextJS!\n\nThis course covers both the NextJS \"App Router\" & the \"Pages Router\" - two different approaches for building fullstack apps with React! And, of course, you\'ll learn what these two approaches are, why two different approaches exist and how each approach works!\n\nI created the bestselling Udemy course on React, now I\'m super excited to share this NextJS with you - an in-depth course about an amazing React framework that allows you to take the next step as a React developer and build real, production-ready projects with React and Next.js!\n\nNext.js is the production-ready, fullstack-capable framework for ReactJS - the most popular JavaScript library you can learn these days!\n\nToo many buzzwords for your taste?\n\nFair enough - but indeed, NextJS is a great choice for growing as a React developer and for taking your React apps to the next level!\n\nBecause NextJS is growing fast and therefore in high demand. And there are good reasons for that: NextJS allows you to build React apps with built-in server-side rendering and page pre-rendering. Building great user experiences and search engine friendly (SEO!) React apps has never been easier!','https://kanbox.vn/wp-content/uploads/2023/02/nextjs-cover.jpg',' - Bạn chỉ cần có kiến thức cơ bản về HTML,CSS, Javascript\n - Chấp nhận khó khăn, không bỏ cuộc khi gặp khó khăn'),(44,'Khóa học Manual Test','Crystal clear understanding on how Software Projects are handled in real time with Live examples\nIn Depth understanding of current Software Testing methodologies with Manual testing tools followed in the Industry\nUnderstand JIRA (Project management tool) and Tester activities in the Jira from Scratch\nThorough knowledge on latest Practices like Agile scrum, SDLC, Testdriven, Behaviour driven approaches\nIn Depth knowledge on writing test cases in Jira using Xray app on Live Project\nStrong exposure on designing test cases and defect life cycle process',199,'Manual Test là quá trình kiểm tra và đánh giá chức năng, tính năng và hiệu suất của một phần mềm, ứng dụng hoặc hệ thống thông qua việc thực hiện các bước kiểm tra và ghi lại kết quả bằng tay, thay vì sử dụng các công cụ tự động hoặc mã script.','2024-03-29 13:56:45.921000','Course last Updated - Jan 2nd - QA Behavioral Interview Questions & Test Plan Prep videos\n   Gain  7+ Years  Real time  experience as a ( Team/QA Lead) level by just following through this course\n\" One Single Course to Master everything in Software World. \" Learn on Agile Scrum Practices, Software Development Life cycle, QA Process, Software Testing methodologies , and Project management tools like Jira, Bugzilla etc.\n\nAre you struggling to know what happens in Software World to deliver the Project ?\nDo you want to gain the Project Lead level skills with out any prior real time experience ?\n\nIf YES this course is right choice for you\n\nI will also be your Life time mentor in giving you right directions for your Project needs','https://akdemy.net/wp-content/uploads/2023/02/Software-Testing-400x300.png',' - None, All prerequisites are taken care as part of course\n - No testing experience is required. Everything is explained from the Scratch in this tutorial'),(45,'Khóa học lập trình Front End','You will learn complete front-end web development from scratch\nYou will learn to build Modern websites Angular & React JS\nYou will learn HTML5 & CSS3 from scratch, & build a full working website\nYou will learn to build Bootstrap 5 Web Applications\nYou will learn Complete JavaScript + ECMA Script 6 (ES6) from scratch\nYou will build front-end Reactive Web applications with CRUD',199,'Front End còn được biết đến như một Client – Side. Hay có thể hiểu đơn giản là giao diện ứng dụng hoặc trang web. Là sự kết hợp giữa các ngôn ngữ lập trình khác nhau như: HTML, CSS hay Java','2024-03-29 14:05:08.972000','Now with over 10 hours of React content.  Just updated on May 15th, 2023.\n\nMassive new React \"expansion pack\" covers: React basics, JSX, props, state, Vite, MaterialUI, hooks, useEffect, React design patterns, and more.\n\nHi! Welcome to the brand new version of The Web Developer Bootcamp, Udemy\'s most popular web development course.  This course was just completely overhauled to prepare students for the 2023 job market, with over 60 hours of brand new content. This is the only course you need to learn web development. There are a lot of options for online developer training, but this course is without a doubt the most comprehensive and effective on the market.  Here\'s why:\n\n - This is the only Udemy course taught by a professional bootcamp instructor with a track record of success.\n\n - 92% of my in-person bootcamp students go on to get full-time developer jobs. Most of them are complete beginners when I start working with them.\n\n - The previous 2 bootcamp programs that I taught cost $14,000 and $21,000.  This course is just as comprehensive but with brand new content for a fraction of the price.\n\n - Everything I cover is up-to-date and relevant to 2022\'s developer job market. This course does not cut any corners. I just spent 8 months redoing this behemoth of a course!\n\n - We build 13+ projects, including a gigantic production application called YelpCamp. No other course walks you through the creation of such a substantial application.\n\n - The course is constantly updated with new content, projects, and modules.  Think of it as a subscription to a never-ending supply of developer training.\n\n - You get to meet my cats and chickens!','https://akdemy.net/wp-content/uploads/2023/04/Frontend-400x300.png',' - All you need is a PC / Laptop\n - Decent internet connectivity'),(46,'Khóa học lập trình PHP Laravel','Auth Systems: Build secure apps with Laravel\'s auth systems.\nRESTful APIs: Develop APIs handling JSON requests/responses.\nDatabase Seeding: Learn migrations and seeding in Laravel.\nEloquent ORM: Explore database management with Eloquent ORM.\nBlade Templating: Use Laravel\'s Blade for managing views\nRouting: Master Laravel\'s critical routing system.\nMVC Architecture: Understand Laravel\'s MVC design pattern.\nLaravel Setup: Learn how to setup and configure Laravel.',299,'Chào mừng bạn đến với Akdemy.net, nơi cung cấp khóa học lập trình PHP Laravel chất lượng cao. Khóa học này sẽ cung cấp cho bạn những kiến thức và kỹ năng cần thiết. Bạn sẽ trở thành một nhà phát triển web thành công sau khóa học này.','2024-03-29 14:05:50.061000','Get started on your Laravel journey with our comprehensive course designed for beginners! Laravel has emerged as the top pick among PHP developers for its simplicity, high performance, and wide-ranging suite of tools that streamline the web development process.\n\nAs PHP 8 unfolds, Laravel is all set to redefine your perception of PHP altogether. It\'s like the Ruby on Rails of the PHP universe, and perhaps even superior. It\'s expressive, fun, fluent, easy to master, and a breeze to use. Both newcomers and seasoned professionals can\'t seem to get enough of it! Now, you have the opportunity to join this community of satisfied individuals who enjoy building things and earn while doing so.\n\nOur Laravel course is all you\'ll ever need to understand everything about this robust framework – setting it up, getting started, and leveraging its powerful capabilities for modern web development. We\'ll cover in depth 5 key projects in 2023:\n\n 1.Task List\n\n 2.Book Reviews\n\n 3.Event Management API\n\n 4.Livewire Poll App\n \n 5.Job Portal (our most extensive project)','https://akdemy.net/wp-content/uploads/2023/04/laravel-icon-new-400x300.png',' - Bring along a foundational understanding of PHP\n - Equipped with the basics of web development'),(47,'Flutter & Dart - The Complete Guide [2024 Edition]','Learn Flutter and Dart from the ground up, step-by-step\nBuild engaging native mobile apps for both Android and iOS\nUse features like Google Maps, the device camera, authentication and much more!\nLearn how to upload images and how to send manual and automated push notifications\nLearn all the basics without stopping after them: Dive deeply into Flutter & Dart and become an advanced developer',299,'Flutter là một framework mã nguồn mở cho phép tạo ứng dụng di động với hiệu năng cao, chất lượng tốt hỗ trợ đa nền tảng, phù hợp với phát triển ứng dụng Android và iOS. Flutter sử dụng ngôn ngữ Dart của chính Google, Flutter rất dễ học, mạnh mẽ, hiệu năng cao và phát triển ứng dụng di động một cách nhanh chóng.','2024-04-04 13:40:39.145000','Discover the power of Flutter and Dart to create stunning, high-performance mobile apps for iOS and Android with the most comprehensive and bestselling Flutter course! With over 30 hours of comprehensive content, this course is the ultimate resource for anyone who wants to build beautiful, responsive, and feature-rich applications from scratch.\n\nLearn from a Bestselling Udemy Instructor: Maximilian Schwarzmüller\n\nI\'m Maximilian Schwarzmüller, a bestselling, top-rated online course instructor with a vast variety of courses on web and mobile development. I\'m excited to be your guide throughout this journey. My goal with this course is to teach you Flutter from the ground up, step-by-step and in a highly practice-oriented way!\n\nUnlock Your Potential in Mobile App Development\n\nThis Flutter & Dart course is designed for absolute beginners with no prior programming experience, as well as for those with existing iOS, Android or other development skills. Through video lessons and hands-on projects, you\'ll learn the ins and outs of Flutter and Dart!\n\nThroughout the course you\'ll build multiple demo apps - ranging from simple to more complex - and, by the end of the course, you\'ll be able to build your own iOS and Android apps with Flutter.','https://akdemy.net/wp-content/uploads/2023/02/flutter2-1-300x200.png','Basic programming language will help but is not a must-have\nYou can use either Windows, macOS or Linux for Android app development - iOS apps can only be built on macOS though\nNO prior iOS or Android development experience is required\nNO prior Flutter or Dart experience is required - this course starts at zero!'),(48,'Ultimate C# Masterclass for 2024','You will gain an in-depth understanding of C#.\nYou will understand how to write high-performance C# code.\nYou will understand the principles of object-oriented programming.\nYou will learn the most useful design patterns.\nYou will learn to write code of excellent quality.\nYou will gain the knowledge necessary for C# job interviews.\nYou will practice your skills by solving exercises in the browser, as well as by creating advanced projects.\nYou will learn how to use Visual Studio like a pro.',219,'In-depth .NET programming course from basics to advanced. Focus on clean code, performance and practice.','2024-04-08 16:01:42.392000','Welcome to the \"Ultimate C# Masterclass\" course! Are you ready to take your knowledge and career to the next level?\n\n47 hours of on-demand videos, 67 coding exercises, 89 quizzes, and 16 assignments. All this is to help you start coding in C# like a pro.\n\nI\'m a .NET Technical Lead with over 10 years of professional experience. I will help you on this journey every step of the way, no matter if you are a complete beginner who has never coded before or a developer who wants to advance to the next level. We will start from the basics, but we will not stop there.\n\nI will not only teach you how to code but also how to code well. Clean code and good design will be our priorities, and design patterns will be shown in practice, not as abstract concepts that seem impossible to be used in real-life challenges. Refactoring will be something we practice all the time.\n\nPrograms written in C# are fast, but we can make them faster. The performance will be discussed in detail. Asynchrony and multithreading will help us improve it even more.\n\nYou will learn more than C# syntax. It is not only a course about the language but a course about programming in general. You will understand why all the things we learn are needed and how to use them right. Moreover, I will show you how they work under the hood, and thanks to that, you will gain an in-depth understanding of C#. This course also covers the development of unit tests using NUnit and Moq libraries.','https://akdemy.net/wp-content/uploads/2023/04/C-400x300.png','No programming experience is needed. I\'ll teach you everything you need to know.\nA computer (Windows/macOS) with an access to the Internet.\nNo paid software is needed; all tools used in this course are free.'),(49,'.NET Core MVC - The Complete Guide 2023 [E-commerce] [.NET8]','Learn structure of ASP NET MVC Core (.NET 8) Project\nLearn structure of ASP NET Core (.NET 8) Razor Project\nLearn basic fundamentals of ASP NET MVC Core (.NET 8)\nBuild 2 Projects throughout the course',399,'Build real world e-commerce application using ASP.NET Core MVC, Entity Framework Core and ASP.NET Core Identity.','2024-04-09 16:10:07.456000','This is a Beginner to Advanced level course on .NET 8 that will take you from basics all the way to advance mode. This course is for anyone who is new to ASP.NET Core or who is familiar with ASP.NET and wants to take the first stab at understanding what is different in ASP.NET Core. From there we would be building multiple projects to understand all concepts in .NET 8 as we will deploy our final application on Azure as well as IIS.\n\nThroughout this course, we would understand the evolution of ASP.NET Core, and then we would take a look at the modified files and folder structure.','https://akdemy.net/wp-content/uploads/2023/02/aspnet-300x200.png','3-6 months knowledge of C#\nVisual Studio 2022\nSQL Server Management Studio\n.NET 6');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_class`
--

DROP TABLE IF EXISTS `course_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `opening_day` varchar(255) DEFAULT NULL,
  `schedule_id` bigint DEFAULT NULL,
  `course_id` bigint DEFAULT NULL,
  `teacher_id` bigint DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjfwqqfp545y1f1vek58drca8b` (`schedule_id`),
  KEY `FKaxc7n051rss6qu32phxc5i732` (`course_id`),
  KEY `FK24kqhbnk17thtdrmtvi8qarb3` (`teacher_id`),
  CONSTRAINT `FK24kqhbnk17thtdrmtvi8qarb3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKaxc7n051rss6qu32phxc5i732` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKjfwqqfp545y1f1vek58drca8b` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_class`
--

LOCK TABLES `course_class` WRITE;
/*!40000 ALTER TABLE `course_class` DISABLE KEYS */;
INSERT INTO `course_class` VALUES (1,'JSAA-24324','2024-03-24',1,1,1,0),(2,'JSAA-30324','2024-03-30',2,1,2,0),(20,'JSAA-040124','2024-04-01',4,1,7,0),(21,'MT-040424','2024-04-04',31,44,1,0),(22,'Python-070424','2024-04-07',17,4,1,0),(29,'JSP-041324','2024-04-13',2,1,11,0),(30,'JSP-042024','2024-04-20',1,1,13,0),(31,'JSP-170524','2024-05-17',34,1,11,0),(32,'AWSC-220524','2024-05-22',1,2,7,0),(33,'Manual Test 01','2024-05-22',27,44,1,0);
/*!40000 ALTER TABLE `course_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_class_course_registers`
--

DROP TABLE IF EXISTS `course_class_course_registers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_class_course_registers` (
  `course_class_id` bigint NOT NULL,
  `course_registers_id` bigint NOT NULL,
  PRIMARY KEY (`course_class_id`,`course_registers_id`),
  UNIQUE KEY `UK_en5ot42iuh8giklqio1vkfpwm` (`course_registers_id`),
  CONSTRAINT `FK6wshqq0vrus3yd96w1po8c1k5` FOREIGN KEY (`course_registers_id`) REFERENCES `course_register` (`id`),
  CONSTRAINT `FK7j3o67w0s6n1s6eabtixfju6m` FOREIGN KEY (`course_class_id`) REFERENCES `course_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_class_course_registers`
--

LOCK TABLES `course_class_course_registers` WRITE;
/*!40000 ALTER TABLE `course_class_course_registers` DISABLE KEYS */;
INSERT INTO `course_class_course_registers` VALUES (32,188),(22,189),(33,190),(31,193);
/*!40000 ALTER TABLE `course_class_course_registers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_register`
--

DROP TABLE IF EXISTS `course_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_register` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint DEFAULT NULL,
  `status` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `schedule_id` bigint DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `course_class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe63p3yl5xc3783htpkvxtbfaf` (`course_id`),
  KEY `FKm4fvblpx7vupc7a7erumxwkaa` (`schedule_id`),
  KEY `courseregister_user_idx` (`user_id`),
  KEY `FKpmwaen1m53mjymdittmgkyj4u` (`course_class_id`),
  CONSTRAINT `courseregister_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKe63p3yl5xc3783htpkvxtbfaf` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKm4fvblpx7vupc7a7erumxwkaa` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FKpmwaen1m53mjymdittmgkyj4u` FOREIGN KEY (`course_class_id`) REFERENCES `course_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_register`
--

LOCK TABLES `course_register` WRITE;
/*!40000 ALTER TABLE `course_register` DISABLE KEYS */;
INSERT INTO `course_register` VALUES (157,1,0,13,4,'2024-04-10 09:42:50.812000',NULL),(158,1,0,14,4,'2024-04-10 09:42:50.812000',NULL),(159,1,0,15,4,'2024-04-10 09:42:50.812000',NULL),(160,1,0,16,4,'2024-04-10 09:42:50.812000',NULL),(161,1,0,17,4,'2024-04-10 09:42:50.812000',NULL),(162,1,0,18,4,'2024-04-10 09:42:50.812000',NULL),(163,1,0,19,4,'2024-04-10 09:42:50.812000',NULL),(164,1,0,20,4,'2024-04-10 09:42:50.812000',NULL),(188,2,1,42,1,'2024-05-21 22:26:02.492000',32),(189,4,1,42,17,'2024-05-21 22:26:44.291000',22),(190,44,1,42,27,'2024-05-22 00:52:04.567000',33),(191,1,0,58,13,'2024-05-23 14:31:49.103000',NULL),(193,1,1,42,34,'2024-05-23 15:09:35.987000',31);
/*!40000 ALTER TABLE `course_register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_schedules`
--

DROP TABLE IF EXISTS `course_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_schedules` (
  `course_id` bigint NOT NULL,
  `schedules_id` bigint NOT NULL,
  PRIMARY KEY (`course_id`,`schedules_id`),
  KEY `FKfu916k3jorxs9ssc518ssq4fr` (`schedules_id`),
  CONSTRAINT `FK3cai9d19s091pdcqv5htogun9` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKfu916k3jorxs9ssc518ssq4fr` FOREIGN KEY (`schedules_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_schedules`
--

LOCK TABLES `course_schedules` WRITE;
/*!40000 ALTER TABLE `course_schedules` DISABLE KEYS */;
INSERT INTO `course_schedules` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(45,1),(46,1),(47,1),(48,1),(49,1),(1,2),(2,2),(3,2),(4,2),(5,2),(45,2),(46,2),(47,2),(48,2),(49,2),(1,3),(2,3),(3,3),(4,3),(5,3),(45,3),(46,3),(49,3),(1,4),(2,4),(3,4),(4,4),(5,4),(46,4),(49,4),(1,5),(46,5),(47,5),(49,5),(1,6),(47,6),(49,6),(1,7),(47,7),(48,7),(49,7),(1,8),(45,8),(49,8),(1,9),(47,9),(49,9),(1,10),(47,10),(49,10),(1,11),(45,11),(48,11),(1,12),(48,12),(1,13),(1,14),(45,16),(4,17),(4,18),(44,24),(48,24),(44,25),(48,26),(44,27),(48,27),(48,29),(44,30),(44,31),(48,33),(1,34);
/*!40000 ALTER TABLE `course_schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_subjects`
--

DROP TABLE IF EXISTS `course_subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_subjects` (
  `course_id` bigint NOT NULL,
  `subjects_id` bigint NOT NULL,
  PRIMARY KEY (`course_id`,`subjects_id`),
  KEY `FKmd11ib1e2ndfmsh5oav2jh4pa` (`subjects_id`),
  CONSTRAINT `FK4iwinbhnj626k7i5dwgu290k6` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKmd11ib1e2ndfmsh5oav2jh4pa` FOREIGN KEY (`subjects_id`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_subjects`
--

LOCK TABLES `course_subjects` WRITE;
/*!40000 ALTER TABLE `course_subjects` DISABLE KEYS */;
INSERT INTO `course_subjects` VALUES (1,1),(47,1),(1,2),(47,2),(1,3),(47,3),(1,4),(1,5),(2,25),(2,26),(2,27),(2,28),(2,29),(3,30),(3,31),(3,32),(3,33),(4,34),(4,35),(4,36),(4,37),(4,38),(4,39),(4,40),(4,41),(4,42),(4,43),(4,44),(4,45),(4,46),(4,47),(5,48),(5,49),(5,50),(5,51),(44,52),(44,53),(44,54),(44,55),(44,56),(45,57),(45,58),(45,59),(45,60),(45,61),(46,62),(46,63),(46,64),(46,65),(1,67),(1,68),(1,69),(1,70),(1,71),(49,71),(48,73),(49,73),(48,74),(49,74),(48,75),(49,75);
/*!40000 ALTER TABLE `course_subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgot_password`
--

DROP TABLE IF EXISTS `forgot_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_password` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiration_date` datetime(6) DEFAULT NULL,
  `otp` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ss96nm4ed1jmllpxib14p1r7v` (`user_id`),
  CONSTRAINT `FKjfa13lhndn1q66kheuyjk2i5l` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_password`
--

LOCK TABLES `forgot_password` WRITE;
/*!40000 ALTER TABLE `forgot_password` DISABLE KEYS */;
INSERT INTO `forgot_password` VALUES (7,'2024-05-23 15:12:25.088000',420401,42);
/*!40000 ALTER TABLE `forgot_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_account`
--

DROP TABLE IF EXISTS `mail_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `port` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_account`
--

LOCK TABLES `mail_account` WRITE;
/*!40000 ALTER TABLE `mail_account` DISABLE KEYS */;
INSERT INTO `mail_account` VALUES (1,'vanan22022002@gmail.com','Mail Server','ugia vfmo hrbw yapz','smtp.gmail.com',587),(7,'vanan22022002@gmail.com','Mail Sale','ugia vfmo hrbw yapz','smtp.gmail.com',587);
/*!40000 ALTER TABLE `mail_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mail_template`
--

DROP TABLE IF EXISTS `mail_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mail_template` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `body` longtext,
  `subject` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mail_account_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd6u1fghcnbfk0cmgayh1y16ga` (`mail_account_id`),
  CONSTRAINT `FKd6u1fghcnbfk0cmgayh1y16ga` FOREIGN KEY (`mail_account_id`) REFERENCES `mail_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mail_template`
--

LOCK TABLES `mail_template` WRITE;
/*!40000 ALTER TABLE `mail_template` DISABLE KEYS */;
INSERT INTO `mail_template` VALUES (1,'Kính gửi: [name]\nChúng tôi rất vui mừng thông báo rằng bạn đã đăng kí khóa học [course name] tại trung tâm của chúng tôi thành công. Chúng tôi rất mong được gặp bạn trong khóa học.\nBạn vui lòng đến địa chỉ sau để hoàn tất thủ tục nhập học. Dưới đây là một số thông tin chi tiết:\n- Thời gian làm thủ tục: ....\n- Địa chỉ: ...\n- Yêu cầu mang theo: ...\nVui lòng đăng nhập đường link sau để cập nhật thông tin lớp học:\nhttp://localhost:8083/#/login\nusername: [username]  hoặc [useremail]\npassword: [password]\nNếu bạn cần thêm bất kỳ thông tin nào hoặc có bất kỳ câu hỏi nào, đừng ngần ngại liên hệ với chúng tôi qua số điện thoại 0865447197 hoặc email [email].\nChúng tôi  hy vọng rằng bạn sẽ có một trải nghiệm học tập thú vị và bổ ích\nTrân trọng,\n....','Thông báo: Đăng kí khóa học thành công ','register',7),(4,'Your verification OTP code is: [otp]','OTP Verification','verify email',1),(5,'Xin chào [user_name],\n\nChúng tôi vui mừng thông báo rằng bạn đã được thêm vào lớp học [class_name] của khóa học [course_name]. Dưới đây là các thông tin chi tiết về lớp học:\n- Tên lớp học: [class_name]\n- Giảng viên: [teacher_name]\n- Ngày khai giảng: [start_day]\n- Thời gian: [day_one] ~ [day_two]\n- Địa điểm: [Phòng học hoặc địa chỉ trực tuyến]\n\nNhững việc cần lưu ý:\n- Chuẩn bị tài liệu: Bạn vui lòng chuẩn bị các tài liệu cần thiết trước khi tham gia lớp học. Tài liệu có \n  thể được tải về từ hệ thống quản lý học tập của chúng tôi tại [Liên Kết].\n- Tham gia đúng giờ: Để đảm bảo không bỏ lỡ bất kỳ phần nào của bài giảng, bạn vui lòng tham gia lớp học đúng giờ.\n\nLiên hệ: Nếu có bất kỳ câu hỏi hoặc cần hỗ trợ, bạn có thể liên hệ với chúng tôi qua email [mail_send] hoặc số điện thoại [phone].\n\nChúng tôi hy vọng rằng bạn sẽ có những trải nghiệm học tập thú vị và bổ ích tại lớp học này. Chúc bạn học tập hiệu quả và đạt được những kết quả tốt nhất.\n\nTrân trọng,\n\nNguyen Van An\nTrung Tâm Giáo Dục HOCMAIEDUCATION\n[mail_send]\n[phone]',' Thông Báo: Bạn Đã Được Thêm Vào Lớp Học','added student in class',1);
/*!40000 ALTER TABLE `mail_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `option1` varchar(255) DEFAULT NULL,
  `option2` varchar(255) DEFAULT NULL,
  `option3` varchar(255) DEFAULT NULL,
  `option4` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `correct_answer` varchar(255) DEFAULT NULL,
  `topic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (7,'To define computed properties','To define methods for comoponent','To define variables','To handle user interactions','What is the main purpose of the \'data\' instance in a Vue component?','To define variables','VueJs'),(8,'As a method inside the \'methods\' instance',' As a variable inside the \'methods\' instance',' As a method inside the \'computed\' instance',' As a variable inside the \'computed\' instance','How do you define a computed property in Vue',' As a variable inside the \'computed\' instance','VueJs'),(9,'v-for','v-bind','v-model','v-show','Which Vue directive is used to conditionally display an element?','v-show','VueJs'),(10,'JavaScript','Python','C','C++','Which language is Vue written in?','JavaScript','VueJs'),(11,'v-bind','v-model','v-if','v-for','Which Vue directive is used to conditionally render an element?','v-if','VueJs'),(12,'v-show','v-for','v-bind','v-if','Which Vue directive is used for list rendering?','v-for','VueJs'),(13,'v-model','v-bind','v-show','v-for','Which Vue directive is used to create a two-way binding between a form input and a data property?','v-model','VueJs'),(14,' No, watchers can only monitor data properties, never create side effects',' There is no such thing as watchers in Vue.','Yes, watchers are made to create side effects when a monitored data property reaches a certain point, that is the intention ','Maybe','We can make a \'watcher\' in Vue to monitor a data property and create side effects.','Yes, watchers are made to create side effects when a monitored data property reaches a certain point, that is the intention ','VueJs'),(15,'Yes ',' Only the old data property value is available',' Only the new data property value is available',' Neither the old nor the new data property values are available','When a \'watcher\' runs, both the old and the new data property values are available as arguments to the watcher method.','Yes ','VueJs'),(16,' <p>// message //</p>',' <p>## message ##</p>','<p>{{ message }}</p>',' <p>\'\' message \'\'</p>','To display the value from the data property \'message\' inside a <p> tag with text interpolation, what is the correct syntax?','<p>{{ message }}</p>','VueJs'),(17,' To define the component\'s slots before the component is rendered',' To watch data property changes',' To define computed properties','To perform side effects after the component has been inserted into the DOM','What is the main purpose of the \'mounted\' lifecycle hook in a Vue component?','To perform side effects after the component has been inserted into the DOM','VueJs'),(18,' views.js','main.js',' settings.js',' config.js','Vue uses a file to initialize the root Vue instance and mount it to the DOM, what is the file\'s name?','main.js','VueJs'),(19,' beforeMount',' beforeCreate','mounted',' created','In which lifecycle hook is the DOM accessible and ready for manipulation?','mounted','VueJs'),(20,' To make data locally available to a specific parent component','To make data available to other components',' To keep methods local to a single component',' To make a strict interface of data shared between components','What is the purpose of the provide() method?','To make data available to other components','VueJs'),(21,'To get data in a component that is provided by another component',' To create a global funtion',' To include 3rd party libraries',' To smoothly integrate the iframe tag in Vue','What is the purpose of the inject() method?','To get data in a component that is provided by another component','VueJs'),(22,' To handle errors thrown in component methods',' To handle errors thrown in component watchers',' To handle errors thrown in component lifecycle hooks','To handle errors thrown in any of the cases mentioned','What is the purpose of the \'errorCaptured\' lifecycle hook?','To handle errors thrown in any of the cases mentioned','VueJs'),(23,' Using the transition() method','Using the <Transition> wrapper element',' Using the v-transition directive',' Transitions are not supported in Vue','How can you add a transition effect to an element in Vue?','Using the <Transition> wrapper element','VueJs'),(24,' number',' note',' elementID','key','Which special Vue attribute is recommended to provide when generating elements with v-for?','key','VueJs'),(25,' run vue in developer mode','npm run vue',' run dev mode',' compile to dev','What command do we need to write to run our Vue project in developer mode?','npm run vue','VueJs'),(26,'<style scoped>',' <style onlyHere>',' <style this>',' <style local>','What attribute must be used to make the CSS rules inside the <style> tag of a component local to only that specific component?','<style scoped>','VueJs'),(27,' as text between the components start tag and end tag',' as a special wrapper tag','as a special attribute',' as a method','How is data passed to a component using props?','as a special attribute','VueJs'),(28,'All of the techniques mentioned',' Provide/Inject',' Fallthrough Attributes',' Props','In what ways can we pass data from a parent to a child component?','All of the techniques mentioned','VueJs'),(29,'<Slot>','<component>','<DynamicComponent>',' <KeepAlive>','Which special tag is used to make dynamic components in Vue?','Fallthrough Attributes','VueJs'),(30,' is','dynamic','componentName','switch','Which attribute is used with the <component> tag so that we can switch between components?','<component>','VueJs'),(31,' print (\"Hello World\");',' Console.WriteLine(\"Hello World\");',' echo(\"Hello World\");',' System.out.println(\"Hello World\");','What is a correct syntax to output \"Hello World\" in Java?',' System.out.println(\"Hello World\");','Java Core'),(33,'Computer Style Sheets','Creative Style Sheets','Cascading Style Sheets  ','Colorful Style Sheets','What does CSS stand for?','Cascading Style Sheets  ','CSS'),(34,'<link rel=\"stylesheet\" type=\"text/css\" href=\"mystyle.css\">','<stylesheet>mystyle.css</stylesheet>','<style src=\"mystyle.css\">','<stylesheet src=\"mystyle.css\">','What is the correct HTML for referring to an external style sheet?','<style src=\"mystyle.css\">','CSS'),(35,'In the <body> section','At the end of the document','In the <head> section  ','In the <stylesheet> section  ','Where in an HTML document is the correct place to refer to an external style sheet?','In the <head> section  ','CSS'),(36,'<css>','<style>  ','<script>','<body>','Which HTML tag is used to define an internal style sheet?','<style>  ','CSS'),(37,'font','styles','class','style','Which HTML attribute is used to define inline styles?','style','CSS'),(38,'body:color=black;','{body:color=black;}','body {color: black;}','{body;color:black;}','Which is the correct CSS syntax?','body {color: black;}','CSS'),(39,'/* this is a comment */ ','\' this is a comment','// this is a comment','// this is a comment //','How do you insert a comment in a CSS file?','/* this is a comment */ ','CSS'),(40,'background-color','bgcolor','color','color-background','Which property is used to change the background color?','background-color','CSS'),(41,'h1 {background-color:#FFFFFF;}','h1.all {background-color:#FFFFFF;}','all.h1 {background-color:#FFFFFF;}','#h1 {background-color:#FFFFFF;}','How do you add a background color for all <h1> elements?','h1 {background-color:#FFFFFF;}','CSS'),(42,'color','fgcolor','text-color','bg-color','Which CSS property is used to change the text color of an element?','color','CSS'),(43,'font-size ','text-size','text-style','font-style','Which CSS property controls the text size?','font-size ','CSS'),(44,'<p style=\"text-size:bold;\">','<p style=\"font-size:bold;\">','p {text-size:bold;}','p {font-weight:bold;}  ','What is the correct CSS syntax for making all the <p> elements bold?','p {font-weight:bold;}  ','CSS'),(45,'a {text-decoration:none;}  ','a {underline:none;}','a {decoration:no-underline;}','a {text-decoration:no-underline;}','How do you display hyperlinks without an underline?','a {text-decoration:none;}  ','CSS'),(46,'You can\'t do that with CSS','transform:capitalize','text-transform:capitalize  ','text-style:capitalize','How do you make each word in a text start with a capital letter?','text-transform:capitalize  ','CSS'),(47,'font-family  ','font-style','font-weight','font-bold','Which property is used to change the font of an element?','font-weight','CSS'),(48,'font-weight:bold;  ','font:bold;','style:bold;','style:font-bold;','How do you make the text bold?','font-weight:bold;  ','CSS'),(49,'padding-left','indent','margin-left  ','left  ','Which property is used to change the left margin of an element?','margin-left  ','CSS'),(50,'demo','.demo','*demo','#demo  ','How do you select an element with id \'demo\'?','#demo  ','CSS'),(51,'#test','test','.test  ','*test','How do you select elements with class name \'test\'?','.test  ','CSS'),(52,'// This is a comment  ','/* This is a comment','# This is a comment','*# This is a comment','How do you insert COMMENTS in Java code?','// This is a comment  ','Java Core'),(53,'string','myString','String  ','Txt','Which data type is used to create a variable that should store text?','String  ','Java Core'),(54,'int x = 5; ','float x = 5;','num x = 5','x = 5;','How do you create a variable with the numeric value 5?','int x = 5; ','Java Core'),(55,'float x = 2.8f; ','byte x = 2.8f','x = 2.8f;','int x = 2.8f;','How do you create a variable with the floating number 2.8?','float x = 2.8f; ','Java Core'),(56,'length()  ','getSize()','len()','getLength()','Which method can be used to find the length of a string?','length()  ','Java Core'),(57,'The * sign','The & sign','The + sign ','The ++ sign ','Which operator is used to add together two values?','The + sign ','Java Core'),(58,'touppercase() ','upperCase()','tuc()','toUpperCase()  ','Which method can be used to return a string in upper case letters?','toUpperCase()  ','Java Core'),(59,'=','<>','><','== ','Which operator can be used to compare two values?','== ','Java Core'),(60,'[]  ','()','{}','\"\"','To declare an array in Java, define the variable type with:','[]  ','Java Core'),(61,'(methodName)','methodName()  ','methodName.','methodName[]','How do you create a method in Java?','methodName()  ','Java Core'),(62,'className','class  ','class()','MyClass','Which keyword is used to create a class in Java?','class  ','Java Core'),(63,'class MyClass = new myObj();','class myObj = new MyClass();','MyClass myObj = new MyClass(); ','new myObj = MyClass();','What is the correct way to create an object called myObj of MyClass?','MyClass myObj = new MyClass(); ','Java Core'),(64,'Math.maximum(x,y)','Math.largest(x,y)','Math.max(x,y)  ','Math.maxNum(x,y)','Which method can be used to find the highest value of x and y?','Math.max(x,y)  ','Java Core'),(65,'* ','#','%','x','Which operator is used to multiply numbers?','* ','Java Core'),(66,'package','getlib','import  ','lib','Which keyword is used to import a package from the Java API library?','import  ','Java Core'),(67,'while (x > y)  ','x > y while {','while x > y {','while x > y:','How do you start writing a while loop in Java?','while (x > y)  ','Java Core'),(68,'return','get','break','void','Which keyword is used to return a value inside a method?','return','Java Core'),(69,'stop','exit','break  ','return','Which statement is used to stop a loop?','break  ','Java Core'),(70,'<script>  ','<js>','<javascript>','<scripting>','Inside which HTML element do we put the JavaScript?','<script>  ','JavaScript'),(71,'msg(\"Hello World\");','msgBox(\"Hello World\");','alert(\"Hello World\");  ','alertBox(\"Hello World\");','How do you write \"Hello World\" in an alert box?','alert(\"Hello World\");  ','JavaScript'),(72,'function:myFunction()','function = myFunction()','function myFunction()  ','function :myFunction()  ','How do you create a function in JavaScript?','function myFunction()  ','JavaScript'),(73,'call function myFunction()','myFunction()  ','call myFunction()','function myFunction()','How do you call a function named \"myFunction\"?','myFunction()  ','JavaScript'),(74,'if i = 5 then','if i == 5 then','if i = 5','if (i == 5)  ','How to write an IF statement in JavaScript?','if (i == 5)  ','JavaScript'),(75,'if (i != 5)  ','if i <> 5','if (i <> 5)','if i =! 5 thenv','How to write an IF statement for executing some code if \"i\" is NOT equal to 5?','if (i != 5)  ','JavaScript'),(76,'while (i <= 10)  ','while (i <= 10; i++)','while i = 1 to 10','while i = 1 : 10','How does a WHILE loop start?','while (i <= 10)  ','JavaScript'),(77,'for i = 1 to 5','for (i = 0; i <= 5; i++)  ','for (i <= 5; i++)','for (i = 0; i <= 5)','How does a FOR loop start?','for (i = 0; i <= 5; i++)  ','JavaScript'),(78,'<!--This is a comment-->','//This is a comment  ','\'This is a comment','#This is a comment','How can you add a comment in a JavaScript?','//This is a comment  ','JavaScript'),(79,'var colors = (1:\"red\", 2:\"green\", 3:\"blue\")','var colors = [\"red\", \"green\", \"blue\"]  ','var colors = \"red\", \"green\", \"blue\"','var colors = 1 = (\"red\"), 2 = (\"green\"), 3 = (\"blue\")','What is the correct way to write a JavaScript array?','var colors = [\"red\", \"green\", \"blue\"]  ','JavaScript'),(80,'Math.round(7.25)','rnd(7.25)','round(7.25)','Math.rnd(7.25)','How do you round the number 7.25, to the nearest integer?','Math.round(7.25)','JavaScript'),(81,'ceil(x, y)','top(x, y)','Math.max(x, y)  ','Math.ceil(x, y)','How do you find the number with the highest value of x and y?','Math.max(x, y)  ','JavaScript'),(82,'onclick  ','onchange','onmouseclick','onmouseover','Which event occurs when the user clicks on an HTML element?','onclick  ','JavaScript'),(83,'=  ','-','*','x','Which operator is used to assign a value to a variable?','=  ','JavaScript'),(84,'false','NaN','true  ','0','What will the following code return: Boolean(10 > 9)','true  ','JavaScript'),(85,'Google','The World Wide Web Consortium  ','Microsoft','Mozilla','Who is making the Web standards?','The World Wide Web Consortium  ','HTML'),(86,'<head>','<h6>','<heading>','<h1>  ','Choose the correct HTML element for the largest heading:','<h1>  ','HTML'),(87,'<important>','<i>','<b>','<strong>  ','Choose the correct HTML element to define important text','<strong>  ','HTML'),(88,'<a>http://www.w3schools.com</a>','<a url=\"http://www.w3schools.com\">W3Schools.com</a>','<a href=\"http://www.w3schools.com\">W3Schools</a>','<a name=\"http://www.w3schools.com\">W3Schools.com</a>','What is the correct HTML for creating a hyperlink?','<a href=\"http://www.w3schools.com\">W3Schools</a>','HTML'),(89,'^','*','/  ','<','Which character is used to indicate an end tag?','/  ','VueJs'),(90,'<thead><body><tr>','<table><head><tfoot>','<table><tr><td>  ','<table><tr><tt>','Which of these elements are all <table> elements?','<table><tr><td>  ','HTML'),(91,'<dl>','<ul>','<ol>  ','<list>','How can you make a numbered list?','<ol>  ','HTML'),(92,'<dl>','<list>','<ul> ','<ol>','How can you make a bulleted list?','<ul> ','HTML'),(93,'<check>','<input type=\"checkbox\">  ','<checkbox>','<input type=\"check\">','What is the correct HTML for making a checkbox?','<input type=\"checkbox\">  ','HTML'),(94,'<input type=\"text\">  ','<input type=\"textfield\">','<textfield>','<textinput type=\"text\">','What is the correct HTML for making a text input field?','<input type=\"text\">  ','HTML'),(95,'<input type=\"dropdown\">','<input type=\"list\">','<list>','<select>  ','What is the correct HTML for making a drop-down list?','<select>  ','HTML'),(96,'<image src=\"image.gif\" alt=\"MyImage\">','<img src=\"image.gif\" alt=\"MyImage\"> ','<img href=\"image.gif\" alt=\"MyImage\">','<img alt=\"MyImage\">image.gif</img>','What is the correct HTML for inserting an image?','<img src=\"image.gif\" alt=\"MyImage\"> ','HTML'),(97,'title','longdesc','alt  ','src','Which HTML attribute specifies an alternate text for an image, if the image cannot be displayed?','alt  ','HTML'),(98,'Specifies a context menu for an element. The menu appears when a user right-clicks on the element','Specify whether the content of an element should be editable or not  ','Return the position of the first found occurrence of content inside a string','Update content from the server','The HTML global attribute, \"contenteditable\" is used to:','Specify whether the content of an element should be editable or not  ','HTML'),(99,'placeholder','validate','required  ','formvalidate','The HTML <canvas> element is used to:','required  ','HTML'),(100,'range','controls','search','slider','Which input type defines a slider control?','range','HTML'),(101,'<range>','<measure>','<gauge>','<meter>  ','Which HTML element is used to display a scalar measurement within a range?','<meter>  ','HTML'),(102,'<top>','<header>  ','<head>','<section>','Which HTML element is used to specify a header for a document or section?','<header>  ','HTML');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` enum('ROLE_USER','ROLE_MODERATOR','ROLE_ADMIN') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MODERATOR'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `day_one` varchar(255) DEFAULT NULL,
  `day_two` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'Thứ 2 18:00-20:00','Thứ 3 18:00-20:00'),(2,'Thứ 3 18:00-20:00','Thứ 4 18:00-20:00'),(3,'Thứ 4 18:00-20:00','Thứ 5 18:00-20:00'),(4,'Thứ 5 18:00-20:00','Thứ 6 18:00-20:00'),(5,'Thứ 6 18:00-20:00','Thứ 7 18:00-20:00'),(6,'Thứ 2 20:00-22:00','Thứ 3 20:00-22:00'),(7,'Thứ 3 20:00-22:00','Thứ 4 20:00-22:00'),(8,'Thứ 5 20:00-22:00','Thứ 6 20:00-22:00'),(9,'Thứ 6 20:00-22:00','Thứ 7 20:00-22:00'),(10,'Thứ 7 20:00-22:00','Chủ Nhật 20:00-22:00'),(11,'Thứ 2 16:00-18:00','Thứ 3 16:00-18:00'),(12,'Thứ 3 16:00-18:00','Thứ 4 16:00-18:00'),(13,'Thứ 4 16:00-18:00','Thứ 5 16:00-18:00'),(14,'Thứ 5 16:00-18:00','Thứ 6 16:00-18:00'),(15,'Thứ 6 16:00-18:00','Thứ 7 16:00-18:00'),(16,'Thứ 7 16:00-18:00','Chủ Nhật 16:00-18:00'),(17,'Thứ 2 8:00-10:00','Thứ 3 8:00-10:00'),(18,'Thứ 3 8:00-10:00','Thứ 4 8:00-10:00'),(19,'Thứ 4 8:00-10:00','Thứ 5 8:00-10:00'),(20,'Thứ 5 8:00-10:00','Thứ 6 8:00-10:00'),(21,'Thứ 6 8:00-10:00','Thứ 7 8:00-10:00'),(22,'Thứ 7 8:00-10:00','Chủ Nhật 8:00-10:00'),(23,'Thứ 2 10:00-12:00','Thứ 3 10:00-12:00'),(24,'Thứ 3 10:00-12:00','Thứ 4 10:00-12:00'),(25,'Thứ 4 10:00-12:00','Thứ 5 10:00-12:00'),(26,'Thứ 5 10:00-12:00','Thứ 6 10:00-12:00'),(27,'Thứ 6 10:00-12:00','Thứ 7 10:00-12:00'),(28,'Thứ 7 10:00-12:00','Chủ Nhật 10:00-12:00'),(29,'Thứ 2 14:00-16:00','Thứ 3 14:00-16:00'),(30,'Thứ 3 14:00-16:00','Thứ 4 14:00-16:00'),(31,'Thứ 4 14:00-16:00','Thứ 5 14:00-16:00'),(32,'Thứ 5 14:00-16:00','Thứ 6 14:00-16:00'),(33,'Thứ 6 14:00-16:00','Thứ 7 14:00-16:00'),(34,'Thứ 7 14:00-16:00','Chủ Nhật 14:00-16:00');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `class_id` bigint DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpqss47h2fevnmkh76r14055o0` (`user_id`),
  KEY `FK56nv285e8l73fru4sw2152y87` (`subject_id`),
  CONSTRAINT `FK56nv285e8l73fru4sw2152y87` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKpqss47h2fevnmkh76r14055o0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2426 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (2392,33,NULL,52,42),(2393,33,NULL,56,42),(2394,33,NULL,55,42),(2395,33,NULL,54,42),(2396,33,NULL,53,42),(2397,32,NULL,26,42),(2398,32,NULL,27,42),(2399,32,NULL,25,42),(2400,32,NULL,28,42),(2401,32,NULL,29,42),(2402,22,NULL,44,42),(2403,22,NULL,45,42),(2404,22,NULL,47,42),(2405,22,NULL,43,42),(2406,22,NULL,37,42),(2407,22,NULL,38,42),(2408,22,NULL,41,42),(2409,22,NULL,42,42),(2410,22,NULL,36,42),(2411,22,NULL,35,42),(2412,22,NULL,34,42),(2413,22,NULL,40,42),(2414,22,NULL,39,42),(2415,22,NULL,46,42),(2416,31,NULL,5,42),(2417,31,NULL,69,42),(2418,31,NULL,71,42),(2419,31,NULL,67,42),(2420,31,NULL,3,42),(2421,31,NULL,70,42),(2422,31,NULL,4,42),(2423,31,'8',1,42),(2424,31,NULL,2,42),(2425,31,NULL,68,42);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Cài đặt, khởi tạo dự án Angular và tạo HomeComponent\nTích hợp Bootstrap 5 vào dự án Angular, chỉnh giao diện trang Home\nThiết kế giao diện với danh sách Product và nút Search,Combobox\nTùy biến Navbar, tách Header Footer cho vào các Component riêng\nThiết kế trang đặt hàng-Order\nThiết kế giao diện trang xác nhận đặt hàng-OrderConfirmed\nGiao diện màn hình đăng nhập vào đăng ký người dùng-Login&Register\nThiết kế trang thông tin chi tiết sản phẩm-DetailProduct\nHướng dẫn download và chạy mã nguồn chương này','Tạo ứng dụng Angular và viết giao diện'),(2,'Cài đặt XAMPP, thiết kế bảng users\nThiết kế bảng tokens, social_accounts, products, categories\nThiết kế bảng roles, orders, order_details\nHướng dẫn download source code','Thiết kế cơ sở dữ liệu cho ứng dụng shopApp'),(3,'Khởi tạo dự án Spring boot\nCài Postman, viết các request GET, POST, PUT, DELETE\nData Transfer Object và Input Validation\nCác action cơ bản với ProductController\nViết request upload và đổi tên 1 file ảnh\nUpload nhiều file ảnh-multiple uploading\nCác method với UserController\nHướng dẫn download source code','Xây dựng ứng dụng Java Spring Backend 1'),(4,'Các api của thực thể orders\nCác request với thực thể order_details\nViết các model Category và Product\nModels cho các thực thể Role, User, ProductImage\nThực thể Order và OrderDetail\nViết các repositories cho Models\nTừ CategoryController gửi các yêu cầu xuống Repositories và Services\nUserController và UserRepository, chưa dùng Spring Security\nCode chức năng thêm sản phẩm mới cho Product Controller\nTách riêng Request thêm Product và request upload ảnh','Xây dựng ứng dụng Java Spring Backend 2'),(5,'Debug chức năng upload ảnh và thêm Product mới\nReponse Object và Response Product list\nThêm dữ liệu fake vào bảng products\nTest lại chức năng phân trang, xem chi tiết, xóa sản phẩm\nChỉnh lại request thêm mới đơn hàng-Order\nChức năng cập nhật và xóa mềm đơn hàng\nViết các chức năng cho chi tiết đơn hàng\nCode các chức năng cho OrderDetail','Xây dựng ứng dụng Java Spring Backend 3'),(25,'AWS Free Tier\nBusiness case for AWS\nAWS Architecture and Compliance\nArchitecture Discussion and Lab Preparation\nPurchasing Domain Names with Route 53\nCreating an S3 Bucket and Hosting our Website\nCreating an SSL Certificate with AWS Certificate Manager\nCreating a CloudFront Distribution','Introduction to AWS for Beginers'),(26,'Routing Traffic with AWS Route 53\nIdentity and Access Management (IAM) - Foundations\nLab Session - Identity and Access Management (IAM) Core Knowledge\nLab Session - Multi Factor Authentication (MFA)\nHands On - Trusted Adviser\nElastic Compute Cloud (EC2)','Dowload Course Recourses & Code for Our AWS Course'),(27,'Lab Session - Connecting to EC2 Linux Instances\nLab Session - Connecting to EC2 Windows Instances\nSimple Storage Service (S3)\nS3 Version Control and Lifecycle Management\nDatabases on AWS\nVirtual Private Cloud\nExploring the default VPC','IT (Information Technology) Fundamental - Deep Dive'),(28,'CloudWatch\nDeployment on AWS\nDemonstration - Blue-Green Deployments on AWS\nCloudFormation\nLab Session -CloudFormation','Introduction to Cloud Computing and AWS-Amazon Web Service'),(29,'AWS Analytics\nPreparing for the AWS Certified Cloud Practitioner Exam','AWS IAM - Identity & Access Management'),(30,'JAVASCRIPT BASIC','JAVASCRIPT BASIC'),(31,'REACT JS BASIC','REACT JS BASIC'),(32,'REACT JS HOOK','REACT JS HOOK'),(33,'REDUX','REDUX'),(34,'BUỔI 1 - HƯỚNG DẪN CÀI ĐẶT MÔI TRƯỜNG PYTHON','BUỔI 1 - HƯỚNG DẪN CÀI ĐẶT MÔI TRƯỜNG PYTHON'),(35,'BUỔI 1 - CÚ PHÁP CƠ BẢN','BUỔI 1 - CÚ PHÁP CƠ BẢN'),(36,'BUỔI 1 - THỰC HÀNH BÀI TẬP','BUỔI 1 - THỰC HÀNH BÀI TẬP'),(37,'BUỔI 2 - CẤU TRÚC VÒNG LẶP:','BUỔI 2 - CẤU TRÚC VÒNG LẶP:'),(38,'BUỔI 2 - XÂY DỰNG HÀM(FUNCTION)','BUỔI 2 - XÂY DỰNG HÀM(FUNCTION)'),(39,'BUỔI 2 - THỰC HÀNH BÀI TẬP','BUỔI 2 - THỰC HÀNH BÀI TẬP'),(40,'BUỔI 3 - XỬ LÝ VỚI STRING:','BUỔI 3 - XỬ LÝ VỚI STRING:'),(41,'BUỔI 3 - CẤU TRÚC DỮ LIỆU CƠ BẢN','BUỔI 3 - CẤU TRÚC DỮ LIỆU CƠ BẢN'),(42,'BUỔI 3 - THỰC HÀNH BÀI TẬP','BUỔI 3 - THỰC HÀNH BÀI TẬP'),(43,'BUỔI 4 - THỰC HÀNH BÀI TẬP','BUỔI 4 - THỰC HÀNH BÀI TẬP'),(44,'BUỔI 5 - SỬ DỤNG CÁC HÀM TOÁN','BUỔI 5 - SỬ DỤNG CÁC HÀM TOÁN'),(45,'BUỔI 5 - SỬ DỤNG DATETIME','BUỔI 5 - SỬ DỤNG DATETIME'),(46,'BUỔI 5 - SỬ DỤNG HÀM ẨN LAMBDA','BUỔI 5 - SỬ DỤNG HÀM ẨN LAMBDA'),(47,'BUỔI 5 - THỰC HÀNH BÀI TẬP','BUỔI 5 - THỰC HÀNH BÀI TẬP'),(48,'Getting Started','Getting Started'),(49,'Optional: React Refresher','Optional: React Refresher'),(50,'NextJs Essentials (App Router)','NextJs Essentials (App Router)'),(51,'Pages & File-based Routing','Pages & File-based Routing'),(52,'CÁC KHÁI NIỆM CƠ BẢN VỀ KIỂM THỬ PHẦN MỀM','CÁC KHÁI NIỆM CƠ BẢN VỀ KIỂM THỬ PHẦN MỀM'),(53,'TESTING IN SLCD (SOFTWARE LIFE CYCLE DEVELOPMENT)','TESTING IN SLCD (SOFTWARE LIFE CYCLE DEVELOPMENT)'),(54,'TESTING TECHNIQUES','TESTING TECHNIQUES'),(55,'PHÂN TÍCH YÊU CẦU (REQUIREMENT ANALYSIS)','PHÂN TÍCH YÊU CẦU (REQUIREMENT ANALYSIS)'),(56,'DATABASE - SQL','DATABASE - SQL'),(57,'HTML 5 VÀ THỰC HÀNH','HTML 5 VÀ THỰC HÀNH'),(58,'CSS VÀ THỰC HÀNH','CSS VÀ THỰC HÀNH'),(59,'CSS - PHÂN BIỆT LESS/SASS','CSS - PHÂN BIỆT LESS/SASS'),(60,'SỬ DỤNG PHOTOSHOP','SỬ DỤNG PHOTOSHOP'),(61,'CHUYỂN FILE PSD THÀNH HTML','CHUYỂN FILE PSD THÀNH HTML'),(62,'Cài đặt và cấu hình Laravel','Cài đặt và cấu hình Laravel'),(63,'Route trong Laravel','Route trong Laravel'),(64,'View trong Laravel','View trong Laravel'),(65,'Form trong Blade Engine','Form trong Blade Engine'),(67,'Cài đặt Spring security, tạo đối tượng UserDetails\nTùy biến các hàm Jwt, Login, Register User\nViết api Login và trả về Jwt Token Key\nLọc Request với doFilterInternal\nPhân quyền USER, ADMIN cho các request đặt hàng\nTạo user admin và phần quyền cho các route còn lại','Spring Security quản lý đăng nhập với JwtToken '),(68,'2-way data binding trong Angular\nValidate Angular Form với ngIf\nGửi request đăng ký người dùng và tùy biến CORS\nGọi api với UserService-Angular\nGọi API đăng nhập user từ Angular\nTích hợp đa ngôn ngữ phía Java Spring Backend\nThực hành viết đa ngôn ngữ và resources cho các controller còn lại\nGọi API Login và lấy JWT token trả về từ Java Spring API\nThêm chức năng chọn Role vào form đăng nhập','Ghép API từ Angular sang Spring Boot Backend - Phần 1'),(69,'Hiển thị sản phẩm và Paging trên Angular và Bootstrap\nSửa lại API lấy danh sách Product\nLọc theo text và danh mục sản phẩm\nGhép API trang Detail Product\nTạo và lưu trữ Giỏ hàng với LocalStorage\nForm xác nhận đơn hàng, tính tổng tiền ','Ghép API từ Angular sang Spring Boot Backend - Phần 2'),(70,'Sửa method createOder trong Backend\nGọi API đặt hàng từ FrondEnd\nSửa lại api lấy thông tin Order\nSửa lại order response và màn hình OrderDetail\nNavigate giữa các màn hình trong Angular\nViết API lấy chi tiết User và lưu Local Storage\nLấy user từ jwt Angular, tùy biến Header\nTạo các biến SCSS dùng chung\nViết API cập nhật thông tin User\nMàn hình cập nhật User trên Angular','Cập nhật các tính năng từ Frontend đến Backend - Phần 1'),(71,'Phân quyền Admin với AdminGuard\nViết thêm Request lọc Order và dùng @PreAuthorize\nMàn hình Admin và các tabs, làm việc với ngSwitchCase\nThêm Log4j bên backend,Nested Routing bên Angular\nThêm chức năng cập nhật và xóa Order cho admin','Cập nhật các tính năng từ Frontend đến Backend - Phần 2'),(73,'72 seconds about what this course is\n105 seconds about what this course isn’t\n97 seconds about practicing what you learn\n112 seconds about time optimization\nInstalling Visual Studio Community\nAccessing the code','Introduction'),(74,'Section introduction\nOur first C# program\nFrom a text file to an executable program\nProgrammer\'s most important skill\nThe goal for this section\nVariables\nNaming variables & introduction to clean code\nVariables\nOperators\nImplicitly typed variables\nUser input','C# Fundamentals'),(75,'Section introduction\nThe issues in our code. A need for Object-Oriented Programming\nIntroduction to object-oriented programming\nUnderstanding OOP with the DateTime type\nBasics of OOP\nAbstraction\nData hiding\nCustom constructor\nHotelBooking class\nC# restrictions on code outside classes. Top-level statements\nAdding methods to classes','Basics of OOP');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_questions`
--

DROP TABLE IF EXISTS `subject_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_questions` (
  `subject_id` bigint NOT NULL,
  `questions_id` bigint NOT NULL,
  PRIMARY KEY (`subject_id`,`questions_id`),
  KEY `FKsac03odjhtoui8f67bwtnm7s8` (`questions_id`),
  CONSTRAINT `FKi6iyayqwrqgr4o4q6q2vmokrt` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  CONSTRAINT `FKsac03odjhtoui8f67bwtnm7s8` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_questions`
--

LOCK TABLES `subject_questions` WRITE;
/*!40000 ALTER TABLE `subject_questions` DISABLE KEYS */;
INSERT INTO `subject_questions` VALUES (1,7),(2,7),(67,7),(69,7),(70,7),(71,7),(1,8),(2,8),(67,8),(69,8),(70,8),(71,8),(1,9),(2,9),(67,9),(69,9),(70,9),(71,9),(1,10),(2,10),(67,10),(69,10),(70,10),(71,10),(1,11),(2,11),(67,11),(69,11),(70,11),(71,11),(1,12),(2,12),(67,12),(69,12),(70,12),(1,13),(2,13),(1,14),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(1,31),(3,31),(4,31),(5,31),(67,31),(68,31),(1,52),(3,52),(4,52),(5,52),(67,52),(68,52),(1,53),(3,53),(4,53),(5,53),(67,53),(68,53),(1,54),(3,54),(4,54),(5,54),(67,54),(68,54),(1,55),(3,55),(4,55),(5,55),(67,55),(68,55),(3,56),(4,56),(5,56),(67,56),(68,56),(3,57),(4,57),(5,57),(67,57),(68,57),(3,58),(4,58),(5,58),(67,58),(68,58),(3,59),(4,59),(5,59),(67,59),(68,59),(3,60),(4,60),(5,60),(67,60),(68,60),(3,61),(4,61),(5,61),(68,61),(3,62),(4,62),(5,62),(68,62),(3,63),(4,63),(5,63),(68,63),(3,64),(4,64),(5,64),(68,64),(3,65),(4,65),(5,65),(68,65),(3,66),(4,66),(5,66),(68,66),(3,67),(4,67),(5,67),(68,67),(3,68),(4,68),(5,68),(68,68),(3,69),(4,69),(5,69),(68,69),(5,70),(67,70),(5,71),(67,71),(5,72),(67,72),(5,73),(67,73),(5,74),(67,74),(67,75),(67,76),(67,77),(67,78),(67,79);
/*!40000 ALTER TABLE `subject_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Teacher An Nguyen','Me Tri, Hanoi','Annv@gmail.com','08654471979',0),(2,'Teacher Hoang','Me Tri, Hanoi','annv22@gmail.com','08654471977',0),(7,'Teacher Dat','Ha Noi','vanan22022002@gmail.com','0865447197',0),(9,'Teacher Phuc','Ha Noi','nguyenvanan_t65@hus.edu.vn','0865447197',0),(11,'Teacher Ngoc Minh','HN','vanan22022002@gmail.com','0865447197',0),(12,'Teacher Jack','Ha Noi','vanan22022002@gmail.com','0865447197',0),(13,'Teacher John Wayne','Ha Noi','vanan22022002@gmail.com','0865447197',0),(14,'Teacher Phong','Ha Noi','vanan22022002@gmail.com','0865447197',0),(15,'Teacher Tam','Ha Noi','vanan22022002@gmail.com','0865447197',0),(16,'Teacher Huyen','Ha Noi','vanan22022002@gmail.com','0865447197',0),(17,'Teacher Hoang Anh','Ha Noi','vanan22022002@gmail.com','0865447197',0),(18,'Teacher Trang','Ha Noi','vanan22022002@gmail.com','0865447197',0),(19,'Teacher Tram','Ha Noi','vanan22022002@gmail.com','0865447197',0);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_classes`
--

DROP TABLE IF EXISTS `user_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_classes` (
  `user_id` bigint NOT NULL,
  `class_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`class_id`),
  KEY `FKclp9rj8hhb20sxf1omau8q2u6` (`class_id`),
  CONSTRAINT `FKclp9rj8hhb20sxf1omau8q2u6` FOREIGN KEY (`class_id`) REFERENCES `course_class` (`id`),
  CONSTRAINT `FKqen46xej8c5lyl9hwmxnoq4s0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_classes`
--

LOCK TABLES `user_classes` WRITE;
/*!40000 ALTER TABLE `user_classes` DISABLE KEYS */;
INSERT INTO `user_classes` VALUES (42,22),(42,31),(42,32),(42,33);
/*!40000 ALTER TABLE `user_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(2,1),(6,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(56,1),(57,1),(58,1),(3,3);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `birthday` varchar(50) DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Annv@gmail.com','$2a$10$nOX3ko14atgKvgxMc3LobOAWRY0R4RXeuKt4o9xYpGTF1eHfp3.1O','Annv','Nguyen Van An','Me Tri, Hanoi','0865447107','2002-02-22',0),(2,'annv22@gmail.com','$2a$10$tVeL0FSsa6cGp43TX5BqjeANAxbMc/tiPA3fYUC.NX81R1/mQM/jW','nva','Nguyen Van Hoang','Me Tri, Hanoi','0865447197','2002-02-22',0),(3,'admin@gmail.com','$2a$10$8lnLNgkj0XBVydNASHacce9qPjoV.iRC2mF1dh4YmkpnI6cgHG/Qi','admin','Le Minh Hieu','Me Tri, Hanoi','0865447107','2002-02-22',0),(6,'vanan220220022@gmail.com','$2a$10$fatrrlmfZDFwkuIyk7hJZ./esw0m4mBhju5.UBjBg009uba/fFVAu','annv1234','An Nguyen Van','Me Tri, Hanoi','0865447107','2002-02-22',0),(7,'john.doe@example.com','password1','johndoe','John Doe','123 Main Street, Cityville, USA','0865447107','1990-01-01',0),(8,'jane.smith@example.com','password2','janesmith','Jane Smith','456 Elm Street, Townsville, USA','0865447107','1991-02-02',0),(9,'michael.johnson@example.com','password3','michaeljohnson','Michael Johnson','789 Oak Street, Villageton, USA','0865447107','1992-03-03',0),(10,'emily.brown@example.com','password4','emilybrown','Emily Brown','012 Pine Street, Hamletville, USA','0865447107','1993-04-04',0),(11,'daniel.wilson@example.com','password5','danielwilson','Daniel Wilson','345 Maple Street, Districttown, USA','0865447107','1994-05-05',0),(12,'olivia.taylor@example.com','password6','oliviataylor','Olivia Taylor','678 Cedar Street, Boroughville, USA','0865447107','1995-06-06',0),(13,'matthew.anderson@example.com','password7','matthewanderson','Matthew Anderson','901 Walnut Street, Suburbia, USA','0865447107','1996-07-07',0),(14,'example8@example.com','password8','user8','Sophia Martinez','234 Birch Street, Territorytown, USA','0865447107','1997-08-08',0),(15,'example9@example.com','password9','user9','William Rodriguez','567 Spruce Street, Municipalityville, USA','0865447107','1998-09-09',0),(16,'example10@example.com','password10','user10','Ava Garcia','890 Ash Street, Provincetown, USA','0865447107','1999-10-10',0),(17,'example11@example.com','password11','user11','Michael Brown','111 Pine Street, Cityville, USA','0865447107','1990-11-11',0),(18,'example12@example.com','password12','user12','Emma Johnson','222 Elm Street, Townsville, USA','0865447107','1991-12-12',0),(19,'example13@example.com','password13','user13','Sophia Williams','333 Oak Street, Villageton, USA','0865447107','1992-11-13',0),(20,'example14@example.com','password14','user14','Matthew Smith','444 Pine Street, Hamletville, USA','0865447107','1993-12-14',0),(21,'example15@example.com','password15','user15','Olivia Jones','555 Maple Street, Districttown, USA','0865447107','1994-10-15',0),(22,'example16@example.com','password16','user16','William Wilson','666 Cedar Street, Boroughville, USA','0865447107','1995-09-16',0),(23,'example17@example.com','password17','user17','Ava Taylor','777 Walnut Street, Suburbia, USA','0865447107','1996-08-17',0),(24,'example18@example.com','password18','user18','Michael Martinez','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(25,'example19@example.com','password18','user19','Vivian Gordon','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(26,'example20@example.com','password18','user20','Hudson Diaz','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(27,'example21@example.com','password18','user21','Aurora Butler','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(28,'example22@example.com','password18','user22','Luke Fisher','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(29,'example23@example.com','password18','user23','Eva Marshall','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(30,'example24@example.com','password18','user24','Lincoln Tucker','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(31,'example25@example.com','password18','user25','Savannah Torres','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(32,'example26@example.com','password18','user26','Gabriel Sullivan','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(33,'example27@example.com','password18','user27','Audrey Coleman','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(34,'example28@example.com','password18','user28','Adam Murray','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(35,'example29@example.com','password18','user29','Zoey Ortiz ','888 Birch Street, Territorytown, USA','0865447107','1997-07-07',0),(41,'VanAn2202@gmail.com','$2a$10$mZMcowLn.C693b6P/ruP1O7XQC5lz7BF9EIGv2LWlUnkOCE9aYGY2','nva6848','Nguyen Van An',NULL,NULL,NULL,0),(42,'vanan22022002@gmail.com','$2a$10$dl8.sU.WfgZ4.RJ2BmS9WOTAEYeABa69YTXElBLyEqYc0F.Tqz3Jq','anv3945','An Nguyen Van','Ha Noi','0865447197',NULL,1),(43,'phamducphuc_t65@hus.edu.vn','$2a$10$lTvZgm.QhQVfs2paL2sB4.Waw.w2jaiyicRufX/3vjGlTWkCNQRnu','pdp1862','Pham Duc Phuc','Ha Noi','0865447197',NULL,0),(44,'Annv123@gmail.com','$2a$10$MBI2.Q9BOulWM6nrDAsEiOHfI.16x56YCsDfm4jwShjtIRL2uuH82','Annv123','Nguyen An','HN','0865447197','2024-04-12',0),(45,'minhngoc22022002@gmail.com','$2a$10$vk0B7.9SMU6/SUKasO3ZAeD1uLkOrzZd.D/HbNBzB/5U/g3sQnPpu','mngoc123','Nguyen Minh Ngoc',NULL,NULL,NULL,0),(46,'nguyenvanan_t65@hus.edu.vn','$2a$10$WMewBdzqWFVrmrEtNDeef.7ElOA2ee4GcLPA8CPfD0tKkpiBFAWI2','anv6510','An Nguyen Van','Ha Noi','0865447197',NULL,0),(56,'advisor@gmail.com','$2a$10$ijh2p8FddNIJ274n/5XUSOBBXisrru0UxZ0kaU5LBzkDz7BNwJBQe','Annv1234567','An Nguyen Van An',NULL,NULL,NULL,0),(57,'ananh123@gmail.com','$2a$10$5DzGq2pcaiuq0X4OflG05O4ZX9bLwiWeuYQy4EgAhcZgttO67d2b.','ananh123@gmail.com','An Nguyen Van An',NULL,NULL,NULL,0),(58,'phutct855@gmail.com','$2a$10$rDqEuGC2rzOnfz7PaZ.3huVKRwQZCc5DFuHTdqTxNHhaQll0YiZ5.','pp5574','pd phu','Ha Noi','0865447197',NULL,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-03 21:26:10
