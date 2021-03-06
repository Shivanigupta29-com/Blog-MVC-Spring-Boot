USE blogsp;

CREATE TABLE `user` (
  `id` INT NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `token` VARCHAR(250) NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `post` (
  `id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `body` VARCHAR(300) NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`));
  
  DESC user;
  DESC post;
  INSERT INTO `user` (`id`, `username`, `password`, `email`, `token`) VALUES
	(1, 'pesho','petur', 'pesho@gmail.com','d2de3a6d8360140858016a2ded29b754'),
	(2, 'ivan','ivan', 'ivan@gmail.com', '88c75c78bfd909aa3e85d341863b09b5'),
	(3, 'gosho','georgi', 'georgi@gmail.com', 'dea212cee54d411bae0bf792869c0468'),
	(4, 'mariika','maria', 'maria@gmail.com', 'f521a51d6472c38b14b9c4d855f658be');
    
    INSERT INTO `post` (`id`, `user_id`, `title`, `body`) VALUES
	(1, 2, 'Jobs and Wages in January', 'Facebook’s recent announcement that it’s readying a version of its social software for workplaces got me thinking about Enterprise 2.0, a topic I used to think a great deal about.'),
	(2, 14, 'Why Is Customer Service Still So Lousy', 'A while back I set up autopayment on the Citi credit card I used for business expenses, and it’s been working fine. Recently, however, I ran up so many travel expenses in a month that I hit my credit limit (the clearest sign yet that I’ve been on the road too much)'),
	(3, 9, 'Business Book of the Year? Maybe. Public Talk Next Week? Definitely.', 'Yesterday we got the good news that The Second Machine Age had been shortlisted for the FT and McKinsey Business Book of the Year Award. Erik and I are floored and very flattered, and looking forward to the award dinner in London in November.'),
	(4, 2, 'This Saturday: The Glass Cage Match at the Boston Book Festival', 'I’ve been involved with the Boston Book Festival since Deborah Porter founded it in 2009, and it’s become one of my favorite events of the year. And since I had a for-real mainstream published book come out this year (as opposed to a self-published glorified pamphlet)'),
	(5, 8, 'MIT’s Second Machine Age Conference in September: Sign up Now', 'I am sorry to brag, but this really is an all-star lineup. If you’re at all interested in technological progress and its implications for our businesses, economies, and societies, you should attend the 2014 Second Machine Age conference.'),
	(6, 1, 'When Using Your Smartphone Can Be the Best Thing for Your Mental Health', 'My last post here took on Zeynep Tufekci and, by extension, others who believe the current trend of using robots and other forms of advanced technology for caregiving is, as she put it, “an abdication of a desire to remain human,'),
	(7, 8, 'Examining the Internet of Things: What’s hype? What’s real?', 'The Internet of Things is one of the biggest buzzwords in technology today, and indeed, it does have the potential to be a truly transformational force in the way that we live and work today. However, if you peel back the “potential” and excitable future-speak surrounding IoT. '),
	(8, 15, 'Is it OK to date someone from work?', 'The short answer: Yes, so long as you write your own script like an adult, and not a senseless fable chaser. The long answer: If you find yourself in a position where a mental assessment between career and courtship is spearheading your journey forward.');
	
SELECT * FROM post;