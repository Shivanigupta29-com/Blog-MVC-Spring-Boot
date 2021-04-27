CREATE DATABASE test;
USE test;

CREATE TABLE `test`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `token` VARCHAR(250) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `test`.`post` (
  `id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `body` VARCHAR(300) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`));

      INSERT INTO `test`.user` (`id`, `username`, `password`, `email`, `token`) VALUES
    	(1, 'pesho','petur', 'pesho@gmail.com',NULL),
    	(2, 'ivan','ivan', 'ivan@gmail.com', NULL),
    		(4, 'mariika','maria', 'maria@gmail.com', NULL);

  INSERT INTO `test`.`post` (`id`, `user_id`, `title`, `body`) VALUES
  	(1, 2, 'Jobs and Wages in January', 'Facebook’s recent announcement that it’s readying a version of its social software for workplaces got me thinking about Enterprise 2.0, a topic I used to think a great deal about.'),
  	(2, 1, 'Why Is Customer Service Still So Lousy', 'A while back I set up autopayment on the Citi credit card I used for business expenses, and it’s been working fine. Recently, however, I ran up so many travel expenses in a month that I hit my credit limit (the clearest sign yet that I’ve been on the road too much)'),
  	(3, 4, 'Business Book of the Year? Maybe. Public Talk Next Week? Definitely.', 'Yesterday we got the good news that The Second Machine Age had been shortlisted for the FT and McKinsey Business Book of the Year Award. Erik and I are floored and very flattered, and looking forward to the award dinner in London in November.'),
  	(5, 2, 'MIT’s Second Machine Age Conference in September: Sign up Now', 'I am sorry to brag, but this really is an all-star lineup. If you’re at all interested in technological progress and its implications for our businesses, economies, and societies, you should attend the 2014 Second Machine Age conference.'),
  	(6, 1, 'When Using Your Smartphone Can Be the Best Thing for Your Mental Health', 'My last post here took on Zeynep Tufekci and, by extension, others who believe the current trend of using robots and other forms of advanced technology for caregiving is, as she put it, “an abdication of a desire to remain human,'),
  	(7, 4, 'Examining the Internet of Things: What’s hype? What’s real?', 'The Internet of Things is one of the biggest buzzwords in technology today, and indeed, it does have the potential to be a truly transformational force in the way that we live and work today. However, if you peel back the “potential” and excitable future-speak surrounding IoT. '),
  	(8, 2, 'Is it OK to date someone from work?', 'The short answer: Yes, so long as you write your own script like an adult, and not a senseless fable chaser. The long answer: If you find yourself in a position where a mental assessment between career and courtship is spearheading your journey forward.');