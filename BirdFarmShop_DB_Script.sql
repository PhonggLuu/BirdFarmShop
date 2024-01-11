USE master
GO

DROP DATABASE IF EXISTS BirdFarmShop
CREATE DATABASE BirdFarmShop
GO

USE BirdFarmShop
GO

DROP TABLE IF EXISTS [User]
CREATE TABLE [User]
(
	[username] VARCHAR(25) NOT NULL,
	[password] VARCHAR(150),
	[full_name] NVARCHAR(50),
	[phone] VARCHAR(15),
	[email] VARCHAR(100),
	[role] VARCHAR(10),
	[address] NVARCHAR(100),
	[point] SMALLINT,
	[register_date] DATE,
	[login_by] VARCHAR(10),
	[status] NVARCHAR(20),
	CONSTRAINT PK_Profile PRIMARY KEY ([username]),
)
GO

DROP TABLE IF EXISTS [BirdBreed]
CREATE TABLE [BirdBreed]
(	
	[breed_id] VARCHAR(10),
	[breed_name] NVARCHAR(50),
	[breed_thumbnail] VARCHAR(MAX),
	CONSTRAINT PK_BirdBreed PRIMARY KEY ([breed_id])
)
GO

DROP TABLE IF EXISTS [Bird]
CREATE TABLE [Bird]
(
	[bird_id] VARCHAR(10) NOT NULL,
	[bird_name] NVARCHAR(50),
	[color] NVARCHAR(50),
	[birthday] DATE,
	[grown_age] SMALLINT,
	[gender] BIT,
	[breed_id] VARCHAR(10),
	[achievement] NVARCHAR(200),
	[reproduction_history] SMALLINT,
	[price] INT,
	[description] NVARCHAR(MAX),
	[dad_bird_id] VARCHAR(10),
	[mom_bird_id] VARCHAR(10),
	[discount] SMALLINT CONSTRAINT DF_Bird_discount DEFAULT 0,
	[status] NVARCHAR(20),
	CONSTRAINT PK_Bird PRIMARY KEY ([bird_id]),
	CONSTRAINT FK_Bird_BirdBreed FOREIGN KEY ([breed_id]) REFERENCES [BirdBreed]([breed_id]),
	CONSTRAINT FK_Dadbird FOREIGN KEY ([dad_bird_id]) REFERENCES [Bird]([bird_id]),
	CONSTRAINT FK_Mombird FOREIGN KEY ([mom_bird_id]) REFERENCES [Bird]([bird_id])
)
GO

DROP TABLE IF EXISTS [CustomerBird]
CREATE TABLE [CustomerBird]
(	
	[customer_id] VARCHAR(25),
	[bird_id]  INT IDENTITY,
	[bird_name] NVARCHAR(50),
	[breed_id] VARCHAR(10),
	[gender] BIT,
	[status] NVARCHAR(20),
	[image_url] VARCHAR(MAX),
	CONSTRAINT PK_CustomerBird PRIMARY KEY ([bird_id]),
	CONSTRAINT FK_CustomerBird_User FOREIGN KEY ([customer_id]) REFERENCES [User]([username]),
	CONSTRAINT FK_CustomerBird_BirdBreed FOREIGN KEY ([breed_id]) REFERENCES [BirdBreed]([breed_id])
)

DROP TABLE IF EXISTS [AccessoryCategory]
CREATE TABLE [AccessoryCategory]
(	
	[category_id] VARCHAR(10),
	[category_name] NVARCHAR(50),
	[category_thumbnail] VARCHAR(MAX),
	CONSTRAINT PK_AccessoryCategory PRIMARY KEY ([category_id])
)
GO

DROP TABLE IF EXISTS [Accessory]
CREATE TABLE [Accessory]
(
	[accessory_id] VARCHAR(10) NOT NULL,
	[accessory_name] NVARCHAR(50),
	[category_id] VARCHAR(10),
	[unit_price] INT,
	[stock_quantity] SMALLINT,
	[description] NVARCHAR(MAX),
	[discount] SMALLINT CONSTRAINT DF_Accessory_discount DEFAULT 0,
	[status] NVARCHAR(20),
	CONSTRAINT PK_Accessory PRIMARY KEY ([accessory_id]),
	CONSTRAINT FK_Accessory_AccessoryCategory FOREIGN KEY ([category_id]) REFERENCES [AccessoryCategory]([category_id])
)
GO

DROP TABLE IF EXISTS [BirdNest]
CREATE TABLE [BirdNest]
(
	[nest_id] VARCHAR(10),
	[nest_name] NVARCHAR(50),
	[breed_id] VARCHAR(10),
	[dad_bird_id] VARCHAR(10),
	[mom_bird_id] VARCHAR(10),
	[baby_quantity] SMALLINT,
	[status] NVARCHAR(20),
	[price] INT,
	[discount] SMALLINT CONSTRAINT DF_BirdNest_discount DEFAULT 0,
	[description] NVARCHAR(MAX),
	CONSTRAINT PK_BirdNest PRIMARY KEY ([nest_id]),
	CONSTRAINT FK_BirdNest_BirdBreed FOREIGN KEY ([breed_id]) REFERENCES [BirdBreed]([breed_id]),
	CONSTRAINT FK_BirdNest_Bird_dadbird FOREIGN KEY ([dad_bird_id]) REFERENCES [Bird]([bird_id]),
	CONSTRAINT FK_BirdNest_Bird_mombird FOREIGN KEY ([mom_bird_id]) REFERENCES [Bird]([bird_id])
)
GO


DROP TABLE IF EXISTS [Order]
CREATE TABLE [Order]
(
	[order_id] VARCHAR(11) NOT NULL,
	[customer] VARCHAR(25),
	[order_date] DATETIME,
	[order_status] NVARCHAR(20),
	[name_receiver] NVARCHAR(50),
	[phone_receiver] VARCHAR(15),
	[address_receiver] NVARCHAR(MAX),
	[payment_status] NVARCHAR(20),
	[payment_type] NVARCHAR(20),
	[total_price] INT,
	[cancel_reason] NVARCHAR(MAX),
	[vnp_txnRef] VARCHAR(20),
	[vnp_date] VARCHAR(20),
	[vnp_transaction] VARCHAR(20),
	CONSTRAINT PK_Order PRIMARY KEY ([order_id]),
	CONSTRAINT FK_Order_User FOREIGN KEY ([customer]) REFERENCES [User]([username])
)
GO

DROP TABLE IF EXISTS [BirdPair]
CREATE TABLE [BirdPair]
(
	[pair_id] INT IDENTITY,
	[order_id] VARCHAR(11),
	[young_bird_price] INT,
	[bird_customer] INT,
	[male_bird_id] VARCHAR(10),
	[female_bird_id] VARCHAR(10),
	[number_egg] INT,
	[number_young_bird]INT,
	[status] NVARCHAR(20),
	CONSTRAINT PK_BirdPair PRIMARY KEY ([pair_id]),
	CONSTRAINT FK_BirdPair_Bird_CustomerBird FOREIGN KEY ([bird_customer]) REFERENCES [CustomerBird]([bird_id]),
	CONSTRAINT FK_BirdPair_Bird_MaleBird FOREIGN KEY ([male_bird_id]) REFERENCES [Bird]([bird_id]),
	CONSTRAINT FK_BirdPair_Bird_FemaleBird FOREIGN KEY ([female_bird_id]) REFERENCES [Bird]([bird_id]),
	CONSTRAINT FK_BirdPair_Order FOREIGN KEY ([order_id]) REFERENCES [Order]([order_id]),
)
GO

DROP TABLE IF EXISTS [OrderItem]
CREATE TABLE [OrderItem]
(	
	[order_item_id] INT IDENTITY,
	[order_id] VARCHAR(11),
	[bird_id] VARCHAR(10),
	[nest_id] VARCHAR(10),
	[accessory_id] VARCHAR(10),
	[pair_id] INT,
	[unit_price] INT,
	[order_quantity] SMALLINT,
	CONSTRAINT PK_OrderItem PRIMARY KEY ([order_item_id]),
	CONSTRAINT FK_OrderItem_Order FOREIGN KEY ([order_id]) REFERENCES [Order]([order_id]),
	CONSTRAINT FK_OrderItem_Bird FOREIGN KEY ([bird_id]) REFERENCES [Bird]([bird_id]),
	CONSTRAINT FK_OrderItem_Nest FOREIGN KEY ([nest_id]) REFERENCES [BirdNest]([nest_id]),
	CONSTRAINT FK_OrderItem_Pair FOREIGN KEY ([pair_id]) REFERENCES [BirdPair]([pair_id]),
	CONSTRAINT FK_OrderItem_Accessory FOREIGN KEY ([accessory_id]) REFERENCES [Accessory]([accessory_id])
)
GO

DROP TABLE IF EXISTS [Feedback]
CREATE TABLE [Feedback]
(
	[feedback_id] INT IDENTITY,
	[customer] VARCHAR(25),
	[order_item_id] INT,
	[rating] SMALLINT,
	[comment] NVARCHAR(MAX),
	[feedback_date] DATETIME,
	CONSTRAINT PK_Feedback PRIMARY KEY ([feedback_id]),
	CONSTRAINT FK_Feedback_User FOREIGN KEY ([customer]) REFERENCES [User]([username]),
	CONSTRAINT FK_Feedback_OrderItem FOREIGN KEY ([order_item_id]) REFERENCES [OrderItem]([order_item_id])
)
GO

DROP TABLE IF EXISTS [PairTracking]
CREATE TABLE [PairTracking]
(
	[tracking_id] INT IDENTITY,
	[pair_id] INT,
	[create_user] VARCHAR(25),
	[content] NVARCHAR(100),
	[date_content] DATETIME,
	CONSTRAINT PK_PairTracking PRIMARY KEY ([tracking_id]),
	CONSTRAINT FK_PairTracking_User FOREIGN KEY ([create_user]) REFERENCES [User]([username]),
	CONSTRAINT FK_PairTracking_BirdPair FOREIGN KEY ([pair_id]) REFERENCES [BirdPair]([pair_id])
)
GO


DROP TABLE IF EXISTS [Image]
CREATE TABLE [Image]
(
	[image_id] INT IDENTITY,
	[image_url] VARCHAR(MAX),
	[is_thumbnail] BIT,
	[bird_id] VARCHAR(10),
	[nest_id] VARCHAR(10),
	[pair_id] INT,
	[accessory_id] VARCHAR(10),
	CONSTRAINT FK_Image_Bird FOREIGN KEY ([bird_id]) REFERENCES [Bird]([bird_id]),
	CONSTRAINT FK_Image_Accessory FOREIGN KEY ([accessory_id]) REFERENCES [Accessory]([accessory_id]),
	CONSTRAINT FK_Image_BirdNest FOREIGN KEY ([nest_id]) REFERENCES [BirdNest]([nest_id]),
	CONSTRAINT FK_Image_BirdPair FOREIGN KEY ([pair_id]) REFERENCES [BirdPair]([pair_id])
)
GO


INSERT INTO [BirdBreed] ([breed_id],[breed_name],[breed_thumbnail])
VALUES
	('australian', N'Vẹt Châu Úc', 'https://runghoangda.com/wp-content/uploads/2022/06/vet-cockatoo-gia-bao-nhieu-6.jpg'),
	('asian', N'Vẹt Châu Á', 'https://bizweb.dktcdn.net/100/326/708/files/top-8-loai-vet-mau-xanh-lam-5.jpg?v=1613884133577'),
	('african', N'Vẹt Châu Phi', 'https://petmeshop.com/wp-content/uploads/2020/09/Vet-African-Grey-2.jpg'),
	('amazon', N'Vẹt Nam Mỹ/Amazon', 'https://bizweb.dktcdn.net/100/326/708/files/blue-fronted-amazon-1-6bde88b3-11f7-49c7-8afb-4535ab69af5e.jpg?v=1614651669559'),
	('macaw', N'Vẹt Nam Mỹ/Macaw', 'https://dustyshutt.files.wordpress.com/2011/06/hyacinth-macaw-0483a.jpg'),
	('other', N'Khác', 'https://photo-cms-viettimes.zadn.vn/w800/Uploaded/2023/aohuooh/2019_01_02/27575948_131535550997960_413989668573413376_n_ylgi.jpg')
GO

INSERT INTO [AccessoryCategory] ([category_id],[category_name],[category_thumbnail])
VALUES
	('care', N'Phụ kiện chăm sóc', 'https://petmeshop.com/wp-content/uploads/2020/09/keo-cat-mong-vet-1-1.png'),
	('cage', N'Lồng & cây đứng', 'https://petmeshop.com/wp-content/uploads/2023/05/cay-dung-de-ban-inox-size-s-loai-a-27x29x14.png'),
	('toy', N'Phụ kiện trang trí - Đồ chơi', 'https://petmeshop.com/wp-content/uploads/2020/09/Clicker-3.png')

INSERT INTO [Bird] ([bird_id], [bird_name], [color], [birthday], [grown_age], [gender], [breed_id], [achievement], [reproduction_history], [price], [description],[dad_bird_id] ,[mom_bird_id] , [discount], [status]) 
VALUES 
	('XT001', N'Vẹt Xích Thái XT001', N'Xanh lá,vàng', '2021-01-01', 3, 1, 'asian', 
	N'Giải 3 cuộc thi Vẹt Châu Á Schauwerbung 2023', 
	0, 6500000, 
	N'Xét ở góc độ sinh học, Xích Thái (Vẹt Má Vàng) thuộc nhóm vẹt rừng đuôi dài có viền cổ – Ringneck/Long-tail Parakeets, với 2 đặc trưng của loài: chim trống trưởng thành có đường viền sẫm màu phía sau gáy (ring) và các cá thể chim đều có đuôi rất dài, chiếm khoảng 1/2 tổng chiều dài toàn thân. Xét ở góc độ vật nuôi – Alexandrine Parakeet được coi là loài vẹt khá thân thiện, dễ thuần, sức đề kháng khá tốt, với tuổi thọ có thể đạt đến 30-40 năm.
	Được đánh giá là loài vẹt có khả năng nhại tốt, thực tế chất lượng nhại giọng nói của Vẹt má vàng thua xa chim Nhồng hoặc vẹt xám Châu Phi. Chim bắt chước giọng người không giỏi lắm, đặc biệt khó bắt chước các từ nhiều nguyên âm o, a. Ghi nhận trên thế giới, chỉ một số ít cá thể có thể nhớ và học được đến khoảng 20 từ.',
	NULL, NULL, 0, N'Hết hàng'),

	('IR001', N'Vẹt Indian Ringneck IR001', N'Xanh dương', '2022-06-01', 4, 0, 'asian', 
	N'Top 4 cuộc thi Hội Đam Mê Lông Vũ TP.HCM 2020-2021', 
	1, 9000000, 
	N'Indian Ringneck hay còn gọi là Rose-ringed Parakeets thực sự là những sinh vật kỳ diệu. Thực vậy, chỉ cần nhìn vào những con chim bạn có thể dễ dàng nhận ra chúng qua màu sắc, cái mỏ nổi và long đuôi dài. Cả chim trống và mái đều có đuôi lớn và dài bao gồm 12 lông, 2 lông đuôi lớn chím phần lớn kích thước của con vẹt. Về màu sắc chúng có những màu phổ biến như xanh lá, trắng, vàng, xanh dương. Một số dòng đặc biệt có các màu sắc khá đẹp như xám khói, tím, xanh pastel… Con trống và mái có vẻ ngoài rất giống nhau tuy nhiên những con vẹt trống khi trưởng thành có thể phân biệt bằng vòng đen, trắng hoặc hồng quanh cổ.
	Ringnecks có nguồn gốc từ Châu Á và Châu Phi và có thể được tìm thấy trong các khu rừng hoặc môi trường khô cằn và cũng xa lạ khi có thể thấy chúng tại các khu đô thị như ở California, Florida và cả vương quốc Anh.
	Giống như hầu hết các loài vẹt, ringneck thông minh và làm vật nuôi tốt. Chúng học hỏi khá nhanh và thích thể hiện. Ngoài ra chúng còn có thể nói và phát âm khá rõ, một số con nói khá tốt như các loài vẹt Xám Châu Phi hay Amazon tuy khả năng ghi nhớ về số lượng từ vựng không tốt bằng.',
	NULL, NULL, 5, N'Còn hàng'),

	('IR002', N'Vẹt Indian Ringneck IR002', N'Trắng', '2021-12-01', 5, 0, 'asian', 
	N'Giải 2 Cuộc Thi Vẹt Châu Á-Australasia 2022', 
	0, 9000000, 
	N'Indian Ringneck hay còn gọi là Rose-ringed Parakeets thực sự là những sinh vật kỳ diệu. Thực vậy, chỉ cần nhìn vào những con chim bạn có thể dễ dàng nhận ra chúng qua màu sắc, cái mỏ nổi và long đuôi dài. Cả chim trống và mái đều có đuôi lớn và dài bao gồm 12 lông, 2 lông đuôi lớn chím phần lớn kích thước của con vẹt. Về màu sắc chúng có những màu phổ biến như xanh lá, trắng, vàng, xanh dương. Một số dòng đặc biệt có các màu sắc khá đẹp như xám khói, tím, xanh pastel… Con trống và mái có vẻ ngoài rất giống nhau tuy nhiên những con vẹt trống khi trưởng thành có thể phân biệt bằng vòng đen, trắng hoặc hồng quanh cổ.
	Ringnecks có nguồn gốc từ Châu Á và Châu Phi và có thể được tìm thấy trong các khu rừng hoặc môi trường khô cằn và cũng xa lạ khi có thể thấy chúng tại các khu đô thị như ở California, Florida và cả vương quốc Anh.
	Giống như hầu hết các loài vẹt, ringneck thông minh và làm vật nuôi tốt. Chúng học hỏi khá nhanh và thích thể hiện. Ngoài ra chúng còn có thể nói và phát âm khá rõ, một số con nói khá tốt như các loài vẹt Xám Châu Phi hay Amazon tuy khả năng ghi nhớ về số lượng từ vựng không tốt bằng.',
	NULL, NULL, 0, N'Đang sinh sản'),

	('XA001', N'Vẹt Xích Ấn XA001', N'Xanh lá', '2022-10-01', 1, 1, 'asian', 
	N'Top 5 The National Cage Bird Show (NCBS) 2022', 
	0, 15000000, 
	N'Vẹt xích Ấn – xích Nepal có nguồn gốc từ vùng đông nam châu Á, bao gồm Nepal, Ấn Độ, Pakistan, và Sri Lanka. Chúng thường sống trong các khu rừng, cánh đồng và vùng đồng cỏ.
	Vẹt xích Ấn có một cái mỏ lớn, màu đen và một đốm màu đỏ trên vai. Một đặc điểm nổi bật của chúng là vòng cổ màu đỏ tươi rực, tạo ra sự tương phản đẹp với bộ lông xanh sẫm. Chúng là những vẹt thông minh và thích thể hiện khả năng nói chuyện. Tuy nhiên, khả năng nói chuyện của từng cá thể có thể khác nhau.',
	NULL, NULL, 0, N'Còn hàng'),

	('CP101', N'Vẹt xám CP101', N'Xám', '2022-10-01', 4, 0, 'african', N'Chưa có', 0, 
	2500000, 
	N'Vẹt Xám African, hoặc African Grey Parrot, là một loài vẹt thông minh và nổi tiếng với bộ lông màu xám, mỏ màu đen, và mắt vàng. Chúng có khả năng học nói và hiểu ngôn ngữ, thể hiện sự tò mò và thông minh cao. Tính cách cá nhân có thể biến đổi, nhưng chung quy là vui vẻ và thân thiện. Việc huấn luyện vẹt Xám African có thể đòi hỏi kiên nhẫn và thời gian do tính cách đôi khi cứng đầu.', 
    NULL, NULL, 15, N'Còn hàng'),

    ('CP201', N'Vẹt Cape Parrot CP201', N'Xám, xanh', '2022-10-01', 4, 1, 'african', N'Chưa có', 0, 
	3000000,
	N'Vẹt Cape Parrot African có bộ lông màu xanh lá cây và đỏ với mỏ màu xám. Tính cách thường thân thiện, nhưng có thể ngại ngùng và thận trọng. Việc huấn luyện có thể khá khó khăn do chúng có tính cách độc lập và đôi khi khó nắm bắt.',
	NULL, NULL, 0, N'Hết hàng'),

    ('CP301', N'Vẹt Senegal CP301', N'Xanh lá, vàng', '2022-10-01', 6, 1, 'african', N'Chưa có', 2, 
	8000000, 
	N'Vẹt Senegal African có bộ lông màu xanh lá cây, đỏ và xám. Tính cách thông minh, thân thiện và tương đối dễ huấn luyện. Chúng là loài vẹt nhỏ tương đối dễ chăm sóc và phù hợp cho người mới nuôi vẹt.', 
	NULL, NULL, 0, N'Còn hàng'),

    ('CP401', N'Vẹt cổ trắng CP401', N'Xanh lá, đỏ', '2022-06-01', 2, 0, 'african', N'Chưa có', 1, 
	19000000, 
	N'Vẹt Cổ Trắng African có bộ lông màu xanh biển động đậm và cổ trắng. Tính cách thông minh, tò mò, và thân thiện, giúp dễ huấn luyện. Chúng có khả năng học nói và thích hòa đồng với con người.', 
	NULL, NULL, 0, N'Đang sinh sản'),

    ('CP501', N'Vẹt Lovebird CP501', N'Xanh, vàng, cam', '2022-01-01', 1, 1, 'african', N'Chưa có', 1, 2000000, 
    N'Vẹt Lovebird African có lông màu rực rỡ, thường là xanh, đỏ và cam. Tính cách thường thân thiện, tò mò và dễ huấn luyện cho việc học các lệnh cơ bản. Tuy nhiên, tính cách có thể thay đổi tùy từng cá thể và đòi hỏi kiên nhẫn trong quá trình huấn luyện.', 
    NULL, NULL, 0, N'Đang sinh sản'),

	('CP601', N'Vẹt xám CP601', N'Xám', '2023-01-01', 4, 1, 'african', N'Chưa có', 0, 
	2500000, 
	N'Vẹt Xám African, hoặc African Grey Parrot, là một loài vẹt thông minh và nổi tiếng với bộ lông màu xám, mỏ màu đen, và mắt vàng. Chúng có khả năng học nói và hiểu ngôn ngữ, thể hiện sự tò mò và thông minh cao. Tính cách cá nhân có thể biến đổi, nhưng chung quy là vui vẻ và thân thiện. Việc huấn luyện vẹt Xám African có thể đòi hỏi kiên nhẫn và thời gian do tính cách đôi khi cứng đầu.', 
    'CP301', NULL, 15, N'Còn hàng'),

	('WA301', N'Vẹt Orange Winged Amazon WA301', N'xanh lá, vàng', '2022-02-01', 1, 1, 'amazon', 
	N'Giải khuyến khích cuộc thi Phong trào vẹt kiểng Sài Gòn 2020', 0, 3000000, 
	N'Vẹt Orange-winged Amazon có lông màu xanh với đốm da cam trên cánh. Tính cách thân thiện và hướng ngoại, chúng có khả năng học nói và huấn luyện dễ dàng hơn so với nhiều loài vẹt khác.', 
	NULL, NULL, 0, N'Hết hàng'),

	('BA602', N'Vẹt Blue-fronted Amazon BA602', N'xanh lá, xanh dương', '2022-10-01', 1, 0, 'amazon', 
	N'Top 2 The Parrot Society UK Show 2021', 0, 3000000, 
	N'Lông của Vẹt Blue-fronted Amazon dày, màu xanh lục và đỏ trên trán. Chúng thông minh, thân thiện và dễ huấn luyện, có khả năng học nói và thực hiện các lệnh đơn giản. Tính cách tùy từng cá thể, nhưng nói chung, chúng có khả năng làm thú cưng đáng yêu và thân thiện.', 
	NULL, NULL, 0, N'Còn hàng'),

	('FA303', N'Vẹt Red Faced Amazon FA303', N'xanh lá, đỏ', '2022-10-01', 1, 1, 'amazon', 
	N'Tham gia World Parrot Trust Parrot Festival 2022', 0, 3000000,
	N'Vẹt Red-faced Amazon (Amazona autumnalis) có lông xanh lá cây với khu vực trán đỏ. Tính cách thân thiện, thông minh, nhưng đôi khi có thể cứng đầu. Có khả năng học nói và học lệnh, tuy nhiên, việc huấn luyện có thể đòi hỏi kiên nhẫn và thời gian.', 
	NULL, NULL, 0, N'Còn hàng'),

	('HA104', N'Vẹt Double Yellow Headed Amazon HA104', N'xanh lá, vàng, đỏ', '2022-08-01', 1, 0, 'amazon', 
	N'Giải 3 Nghị Lượng Chim Vẹt Việt Nam 2022-2023', 0, 4500000,
	N'Vẹt Double Yellow Headed Amazon có lông nền màu xanh với đầu và một phần cổ màu vàng rực rỡ. Chúng thông minh, thân thiện, và dễ huấn luyện. Tính cách tương đối hoà đồng, làm cho chúng trở thành vẹt cảnh lý tưởng và có khả năng học nói rất tốt.', 
	NULL, NULL, 0, N'Còn hàng'),

	('NA505', N'Vẹt Yellow Naped Amazon NA505',N'xanh lá, vàng', '2022-12-01', 1, 0, 'amazon', N'Top 4 Giải thi đấu Chim Vẹt Cảnh Việt Nam 2020', 0, 9000000,
	N'Vẹt Yellow Naped Amazon có lông màu xanh lá cây sáng với một vùng vàng ở gốc cổ và đỉnh đầu. Tính cách thông minh, tò mò, thân thiện và có khả năng học nói. Chúng có thể được huấn luyện tương đối dễ dàng, nhưng yêu cầu sự kiên nhẫn và tương tác đều đặn.', 
	NULL, NULL, 0, N'Hết hàng'),

	('CL201', N'Vẹt Cockatiel Lutino CL201', N'xám, vàng, cam', '2022-12-01', 1, 0, 'australian', N'Chưa có', 0, 2500000,
	N'Vẹt Cockatiel Lutino nổi bật với lớp lông màu vàng cam ánh hồng, đầu có mảng màu tương tự, mắt đỏ rực, và đuôi dài màu cam. Loài vẹt này thường rất tình cảm, thân thiện, và dễ huấn luyện, làm cho nó trở thành một thú cưng lý tưởng cho người mới nuôi vẹt.',
	NULL, NULL, 0, N'Còn hàng'),

	('CW192', N'Vẹt Cockatiel White Face CW192', N'xám, trắng', '2022-12-01', 1, 0, 'australian', N'Chưa có', 1, 3500000,
	N'Lông trắng mịn, mắt đỏ, không vùng màu cam trên mặt. Tính cách thân thiện, tinh quái. Dễ huấn luyện, thích tương tác.',
	NULL, NULL, 0, N'Hết hàng'),

	('RL391', N'Vẹt Rainbow Lory RL391', N'đen, đỏ, tím, xanh lá, vàng', '2022-12-01', 1, 1, 'australian', N'Chưa có', 0, 9500000,
	N'Lông nhiều màu sắc rực rỡ, mỏ cam, tính cách vui vẻ, tươi mới, yêu đời. Yêu thích tương tác và cần chế độ ăn uống đa dạng, hoạt động vui chơi để duy trì sức khỏe tốt và làm phong phú cuộc sống của người nuôi.',
	NULL, NULL, 0, N'Đang sinh sản'),
	
	('RL409', N'Vẹt Red Lory RL409', N'đỏ', '2022-02-01', 1, 1, 'australian',  N'Chưa có', 1, 15000000, 
	N'Lông đỏ tươi sáng, mỏ và mắt đen nổi bật, tính cách hòa đồng, thích hát hò và tương tác, yêu thích ăn uống đa dạng, làm phong phú cuộc sống gia đình bằng niềm vui vẹt.', 
	NULL, NULL, 0, N'Còn hàng'),

	('VE358', N'Vẹt Eclectus VE358', N'Xanh lá', '2022-12-01', 1, 1, 'australian',  N'Chưa có', 1, 25000000, 
	N'Vẹt Eclectus là một loài vẹt đa dạng màu sắc, với đực thường có lông màu xanh smaragd và cái có lông màu đỏ tươi. Chúng có mỏ mạnh mẽ và đôi mắt nâu sáng. Eclectus thích tương tác, thông minh và thích học tiếng nói. Tính cách độc lập và thích khám phá, yêu thích chế độ ăn uống chứa nhiều rau quả tươi ngon.', 
	NULL, NULL, 0, N'Còn hàng'),

	('HM350', N'Vẹt Hahn’s Macaw HM350', N'xanh lá, đỏ', '2022-12-01', 1, 0, 'macaw', N'chưa có',0, 15000000 ,N'Hahn’s Macaw rất thông minh và nổi bật với khả năng nói rất tốt nên chúng vẫn là một lựa chọn tốt cho những người yêu chim muốn có vẹt đuôi dài nhưng chưa sẵn sàng đón tiếp một thành viên quá to lớn.',
	NULL, NULL, 0, N'Còn hàng'),
	
	('YC090', N'Vẹt Yellow Collared Macaw YC090', N'xanh lá', '2022-11-01', 1, 1, 'macaw', N'chưa có',0, 25000000 ,N'Vẹt Yellow-collared Macaw có kích thước nhỏ hơn so với một số loài vẹt khác, với chiều dài khoảng 35-37 cm. Bộ lông của chúng chủ yếu màu xanh da trời và vàng, với cổ màu vàng rực rỡ, đó là nguồn gốc của tên gọi của loài vẹt này. Chúng có vẻ ngoài dễ thương và thu hút, và thường được nuôi làm vật nuôi trong các gia đình',
	NULL, NULL, 0, N'Hết hàng'),
	
	('SM808', N'Vẹt Scarlet Macaw SM808', N'đỏ, vàng, xanh dương', '2022-07-01', 1, 0, 'macaw', N'chưa có',0, 30000000 ,N'Scarlet thông minh, xinh đẹp và cũng rất ham học hỏi. Scarlet được huấn luyện đúng cách sẽ có thể làm được nhiều trò cũng như có tài ăn nói rất giỏi. Scarlet tràn đầy năng lượng, thích chơi đùa và tương tác với chủ nuôi, khá ồn ào và thích gây sự chú ý.',
	NULL, NULL, 0, N'Còn hàng'),
	
	('HM101', N'Vẹt Hyacinth Macaw HM101', N'xanh dương', '2022-07-01', 1, 1, 'macaw', N'chưa có',0, 40000000 ,N'Hyacinth là giống vẹt đắt đỏ nhất thế giới và cũng là giống vẹt được cho là một trong những loài vẹt lớn nhất thế giới. Vẹt khá thuần và trầm tính, và là vật nuôi tuyệt vời. Chúng ngọt ngào, dịu dàng và không thích làm ồn cũng như rất thích vui đùa và gắn bó với gia đình, đặc biệt là chúng rất thân thiện với trẻ con.',
	NULL, NULL, 0, N'Còn hàng'),
	
	('GW125', N'Vẹt Green Winged Macaw GW125', N'đỏ, xanh dương, xanh lá', '2022-02-01', 1, 1, 'macaw', N'chưa có',0, 60000000 ,N'Green Winged thuộc trong top những loài vẹt Nam Mỹ có kích thước lớn nhất, to thứ nhì sau Vẹt Hyacinth. Green-winged tuy lớn con nhưng lại nổi tiếng là loài vẹt hiền hoà, được ví von như gã khổng lồ dịu dàng. Loài vẹt này do đó được yêu thích không phải chỉ bởi bộ lông xinh đẹp với ba màu đỏ, xanh lá và xanh dương nhưng còn vì sự dịu dàng, trầm tính hơn những loài Macaw khác. Do đó, Green-winged được chọn là người bạn đồng hành hoặc vật nuôi gia đình rất tốt.',
	NULL, NULL, 0, N'Còn hàng')	
GO

INSERT INTO [Accessory] ([accessory_id], [accessory_name], [category_id], [unit_price], [stock_quantity], [description], [discount], [status]) 
VALUES 
	('GT001', N'Găng tay bắt vẹt', 'toy', 120000, 50, N'Dùng cho việc huấn luyện vẹt, tránh trầy xước hoặc bị đau', 0, N'còn hàng'),

	('GA001', N'Máy ghi âm', 'toy', 600000, 25, N'Dùng cho việc huấn luyện vẹt nói', 0, N'còn hàng'),

	('CT001', N'Còi huấn luyện vẹt', 'toy', 90000, 15, N'Huấn luyện trong việc nghe tín hiệu', 0, N'còn hàng'),

	('LN001', N'Lồng Ngoại Nhập Lớn Inox Cho Vẹt Size M', 'cage', 13800000, 0,
	N'Lồng nuôi vẹt inox với chất liệu là inox tránh được chuột cắn phá như các lồng gỗ, tre không những thế còn không bị móc mọt gặm. Là lựa chọn tối ưu cho sự an toàn của vẹt.
	Kích thước: 61x52x151cm
	Không gian bên trong lồng lớn.
	Có thể biến tấu khi vẹt sinh sản.
	Chốt khóa chắc chắn và dễ thao tác khóa.
	Nan lồng chắc chắn dễ dàng chịu được tác động của chiếc mỏ vẹt.',
	0, N'hết hàng'),

	('BL001', N'Balo Du Lịch Vận Chuyển Vẹt', 'cage', 500000, 12, 
	N'Bird Farm Shop chuyên bán và cung cấp Balo du lịch ngoại nhập chuyên dụng dành cho vẹt. Thiết kế chuyên dụng, chất liệu bền bỉ, phù hợp cho các loại vẹt size nhỏ và vừa. Giúp bạn đi đâu cũng có thể mang thú cưng đi bên cạnh mình một cách thoải mái nhất, tiện lợi nhất. Thiết kế chắc chắn, hiện đại mang phong cách thời trang.
	Kích thước: Size M (31x28x41)
	Xuất xứ: Đài Loan
	Chất liệu: Nhựa cao cấp',
	5, N'còn hàng'),

	('LM001', N'Lồng Màu (Mái Vòm)', 'cage', 450000, 20,
	N'Lồng chim thép sơn tĩnh điện rất an toàn cho chim. Lồng rất chắc chắn và khoảng không gian rộng cho chú chim thoải mái hơn. Chuồng Nuôi Chim được sản xuất theo tiêu chuẩn công nghiệp bền đẹp, được hàn từ dây thép chất lượng cao, qua quy trình sơn tĩnh điện theo tiêu chuẩn.
	Size: 37x28x46
	Xuất xứ: Đài Loan
	Chất liệu: thép sơn tĩnh điện',
	10, N'còn hàng'),

	('SU231', N'Sữa tắm vẹt', 'care', 80000, 50, 
	N'Loại bỏ mùi hôi, diệt khuẩn, triệt hạ vi trùng.', 
	0, N'còn hàng'),

	('MV157', N'Muối tắm vẹt', 'care', 220000, 30, 
	N'Làm mềm lông, tránh đóng vẩy, giảm stress, làm sạch da lông.', 
	0, N'còn hàng'),

	('OB356', N'Ống bơm bột', 'care', 130000, 50, 
	N'Ống bơm bột cao cấp dành cho vẹt non.', 
	0, N'còn hàng'), 

	('KC213', N'Kéo Cắt Móng Vẹt', 'care', 100000, 50, 
	N'Kéo cắt móng chân chuyên dụng dành cho vẹt với thiết kế cao cấp, an toàn, giúp bạn có thể tự làm đẹp móng chân cho bé vẹt tại nhà.', 
	0, N'còn hàng'),

	('DX202', N'Dây xích đeo chân dành cho chim vẹt', 'toy', 70000, 50, 
	N'Dây xích deo chân dành choc him vẹt giúp bạn giữ vẹt trong khoản không giới hạn', 
	0, N'còn hàng'),

	('NK200', N'Máy Nhiệt Kế Điện Tử Pha Bột Cho Vẹt', 'care', 120000, 25, 
	N'Nhiệt kế điện tử hỗ trợ người nuôi canh đo nhiệt độ bột thích hợp không quá nguội hoặc quá nóng ảnh hưởng đến sức khỏe bé vẹt nhà bạn.', 
	0, N'còn hàng')
GO

INSERT INTO [dbo].[BirdNest] ([nest_id],[nest_name],[breed_id],[dad_bird_id],[mom_bird_id],[baby_quantity],[status],[price],[description])
VALUES
	('BN001', N'Tổ chim Vẹt Xích Thái lai Indian Ringneck', 'asian', 'XT001','IR001', 5, N'Còn hàng', 1200000,
	N'Tổ chim con lai giữa Vẹt Xích Thái (African Grey Parrot) và vẹt Indian Ringneck (Indian Ringneck Parakeet) 
	là một loài chim động cơ hoàn hảo kết hợp sự thông minh và nét đẹp từ cả hai loài cha mẹ. 
	Đây là một loài chim có ngoại hình đa dạng, với nhiều biến thể màu lông và đặc điểm thể loại khác nhau.
	Tổ chim con lai này thường có kích thước trung bình, thừa hưởng hình dáng gọn gàng và thanh lịch từ vẹt Indian Ringneck, với một vóc dáng mảnh mai, đuôi dài và mỏ sắc bén. 
	Màu lông có thể thay đổi phụ thuộc vào biến thể của cha mẹ, nhưng thường là sự kết hợp giữa lông xám mạnh mẽ từ Vẹt Xích Thái và màu sắc rực rỡ từ vẹt Indian Ringneck.
	Tổ chim con lai giữa Vẹt Xích Thái và vẹt Indian Ringneck là một loài chim động cơ hấp dẫn, với sự kết hợp độc đáo giữa ngoại hình và tính cách từ cả hai loài cha mẹ. 
	Chúng là những người bạn thú vị và thông minh cho những người yêu chim, nhưng đòi hỏi sự chăm sóc và tập trung đặc biệt để đảm bảo sức khỏe và hạnh phúc của chúng.'),

	('BN002', N'Tổ chim Vẹt Rainbow Lory lai Red Lory', 'australian', 'RL409','RL391', 4, N'Còn hàng', 480000,
	N'Tổ chim con lai giữa Vẹt Rainbow Lory và Vẹt Red Lory là một sự kết hợp độc đáo giữa hai loài vẹt với ngoại hình và tính cách đa dạng.
	Con lai giữa Vẹt Rainbow Lory và Vẹt Red Lory thường rất thông minh và dễ đào tạo. Chúng có khả năng học nhanh và thích thú với các hoạt động giải đố.
	Tổ chim con lai này có năng lượng dồi dào và thích vận động. Chúng cần được cung cấp đủ lượng thời gian để bay và chơi đùa.
	Tiếng kêu của tổ chim con lai này có thể kế thừa từ cả hai loài cha mẹ. Ví dụ, chúng có thể phát ra tiếng kêu trầm ấm của Vẹt Red Lory hoặc tiếng kêu sôi động và vui nhộn của Vẹt Rainbow Lory.
	Tổ chim con lai giữa Vẹt Rainbow Lory và Vẹt Red Lory là một loài chim thú vị và quyến rũ, mang trong mình sự hòa quyện giữa ngoại hình và tính cách của cả hai loài cha mẹ.')
	
GO

INSERT INTO [dbo].[User] ([username],[password],[full_name],[phone],[email],[role],[address],[point],[register_date],[login_by],[status])
VALUES
	('customer','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Trần Văn Hùng','0912345678','hungxedap@gmail.com','customer',N'353/7 Nguyễn Trãi, phường Nguyễn Cư Trinh, quận 1, TP.HCM',20,'2023-06-15','form','active'),
	('giaphong','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Lưu Gia Phong','0998765432','phonglgbt@gmail.com','customer',N'18/5/6 Trương Phước Phan, phường Bình Trị Đông, quận Bình Tân, TP.HCM',5,'2023-06-15','form','active'),
	('nhathoang','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Trần Nhật Hoàng','0987654321','hoangtn@gmail.com','customer',N'73/7 Trần Bình Trọng, phường 1, quận 5, TP.HCM',11,'2023-06-15','form','active'),
	('huynhduc','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Lê Huỳnh Đức','0923456789','duclh123@gmail.com','customer',N'199/5 Trần Bình Trọng, phường 3, quận 5, TP.HCM',20,'2023-06-15','form','active'),
	('khachhangvip','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Mạc Hoàng Sa','0954321678','hsavn@gmail.com','customer',N'56/1/2A Bùi Minh Trực, phường 5, quận 8, TP.HCM',25,'2023-06-15','form','inactive'),
	('staff','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Trương Lê Minh','0934567890','staff@gmail.com','staff',N'359 Lê Đại Hành, phường 13, quận 11, TP.HCM',0,'2023-06-15','form','active'),
	('staff1','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Trần Công Nhân','0987654321','nhantranvet@gmail.com','staff',N'15 Lê Văn Việt, phường TNP B, quận 9, TP.HCM',0,'2023-06-15','form','inactive'),
	('manager','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Quản Văn Lý','0965432109','manager@gmail.com','manager',N'210 Pasteur, phường Đa Kao, quận 1, TP.HCM',0,'2023-06-15','form','active'),
	('admin','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2OTUyMjkyNTh9.H2Ot0OvkGby0DxT1L4BvPCkeowjnvu5vj2x_jouZF0o',N'Trần Văn Quyền Năng','0978901234','admin@gmail.com','admin',N'102 Ba Tháng Hai, phường 10, quận 10, TPHCM',0,'2023-06-15','form','active')
GO


INSERT INTO [dbo].[Image] ([image_url],[is_thumbnail],[bird_id],[nest_id],[accessory_id])
VALUES
    ('https://petmeshop.com/wp-content/uploads/2020/09/Long-ngoai-nhap-inox-lon-cho-vet-4.jpg', 
    1, NULL, NULL, 'LN001'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Long-ngoai-nhap-inox-lon-cho-vet-6.jpg', 
    0, NULL, NULL, 'LN001'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Long-ngoai-nhap-inox-lon-cho-vet-7.jpg', 
    0, NULL, NULL, 'LN001'),

	('https://petmeshop.com/wp-content/uploads/2020/09/Ba-lo-van-chuyen-vet-0.jpg', 
    0, NULL, NULL, 'BL001'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Ba-lo-van-chuyen-vet-4.jpg', 
    0, NULL, NULL, 'BL001'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Ba-lo-van-chuyen-vet-1.jpg', 
    1, NULL, NULL, 'BL001'),

	('https://vn-test-11.slatic.net/p/aed884246583dca2b6ceb8928efa5d12.jpg',
	0, NULL, NULL, 'CT001'),
	('https://petschoolshop.com/wp-content/uploads/2018/07/coi-huan-luyen-Pet-School-300x300.png',
	1, NULL, NULL, 'CT001'),
	('https://salt.tikicdn.com/cache/w1200/ts/product/9b/2b/9e/c0fc97d9ef09b7493df623d56207be5f.jpg',
	0, NULL, NULL, 'CT001'),

	('https://my-test-11.slatic.net/p/16203ca4750a342dfe17a7a4c88f476c.jpg',
	1, NULL, NULL, 'DX202'),
	('https://my-test-11.slatic.net/p/b688c0597527c039eb28c15ec10acc52.jpg',
	0, NULL, NULL, 'DX202'),
	('https://cdn.yeep.vn/2023/03/051417a5ecab9662e3dd69049f7f93b0.jpg',
	0, NULL, NULL, 'DX202'),

	('https://petmeshop.com/wp-content/uploads/2020/09/May-ghi-am-day-noi-cho-vet-1.png',
	1, NULL, NULL, 'GA001'),
	('https://petmeshop.com/wp-content/uploads/2020/09/May-ghi-am-day-noi-cho-vet-2.png',
	0, NULL, NULL, 'GA001'),
	
	('https://petmeshop.com/wp-content/uploads/2020/09/Gang-tay-bat-vet-1.png',
	1, NULL, NULL, 'GT001'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Gang-tay-bat-vet-3-1.png',
	0, NULL, NULL, 'GT001'),
	('https://petmeshop.com/wp-content/uploads/2023/08/2ecb0ea02a6a46b4907283d6183335f8.png',
	0, NULL, NULL, 'GT001'),

	('https://petmeshop.com/wp-content/uploads/2020/09/keo-cat-mong-vet-2-1.png.webp',
	1, NULL, NULL, 'KC213'),
	('https://petmeshop.com/wp-content/uploads/2020/09/keo-cat-mong-vet-3.png',
	0, NULL, NULL, 'KC213'),
	('https://petmeshop.com/wp-content/uploads/2020/09/keo-cat-mong-vet-4.png',
	0, NULL, NULL, 'KC213'),

	('https://petmeshop.com/wp-content/uploads/2023/05/long-chuyen-dung-size-xs-loai-c-mai-vom-46x46x50.png',
	1, NULL, NULL, 'LM001'),
	('https://petmeshop.com/wp-content/uploads/2023/05/long-chuyen-dung-size-xs-loai-c-mai-vom-46x46x50-4.png',
	0, NULL, NULL, 'LM001'),
	('https://petmeshop.com/wp-content/uploads/2023/05/long-chuyen-dung-size-xs-loai-c-mai-vom-46x46x50-5.png',
	0, NULL, NULL, 'LM001'),

	('https://vuongquocvet.com/wp-content/uploads/2017/10/calci-lux.jpg',
	1, NULL, NULL, 'MV157'),
	('https://vietpetgarden.net/wp-content/uploads/2019/03/calci-lux-canxi-cho-chim-vet.jpg',
	0, NULL, NULL, 'MV157'),
	('https://uspigeons.mercasystems.com/media/catalog/product/cache/46/image/363x/296f4ceddc5caf0a87edd1d1d4b58606/v/e/versele_calcilux_500gr.1629168038.jpg',
	0, NULL, NULL, 'MV157'),
	
	('https://lzd-img-global.slatic.net/g/p/b5be219fbe0d7477907f7089ea4787c2.jpg_720x720q80.jpg',
	1, NULL, NULL, 'NK200'),
	('https://lzd-img-global.slatic.net/g/p/a186fee285be480fa78aff98852d0cd3.jpg_720x720q80.jpg',
	0, NULL, NULL, 'NK200'),
	('https://lzd-img-global.slatic.net/g/p/e31d1301033d1548376c44e5f610b78d.jpg_720x720q80.jpg',
	0, NULL, NULL, 'NK200'),

	('https://petmeshop.com/wp-content/uploads/2020/09/Ong-bom-bot-2.jpg',
	1, NULL, NULL, 'OB356'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Ong-bom-bot-0.jpg',
	0, NULL, NULL, 'OB356'),
	('https://petmeshop.com/wp-content/uploads/2020/09/Ong-bom-bot-1.jpg',
	0, NULL, NULL, 'OB356'),

	('https://petmeshop.com/wp-content/uploads/2020/09/z4529885541947-83b7b45957f59b8ceac057974e951a63-scaled.jpg',
	1, NULL, NULL, 'SU231'),
	('https://petshopthuduc.com/wp-content/uploads/2023/05/sua-tam-2.jpg',
	0, NULL, NULL, 'SU231'),
	('https://petshopthuduc.com/wp-content/uploads/2023/05/sua-tam.jpg',
	0, NULL, NULL, 'SU231'),

	('https://petshopthuduc.com/wp-content/uploads/2023/05/alexandrine-parakeet-1-1.jpg', 
	1, 'XT001', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Psittacula_eupatria_-Kuala_Lumpur_Bird_Park-8a.jpg/300px-Psittacula_eupatria_-Kuala_Lumpur_Bird_Park-8a.jpg', 
	0, 'XT001', NULL, NULL),
	('https://sinhvatkieng.com/wp-content/uploads/2018/09/a430b205297dfc5592a6e37c3ab74a73.jpeg', 
	1, 'XT001', NULL, NULL),

	('https://preview.redd.it/2ef6yghwyg681.jpg?width=500&format=pjpg&auto=webp&s=d686b96a7156e335a31ca31427816d00b740ac4b', 
	1, 'IR001', NULL, NULL),
	('https://vetcanh.com/wp-content/uploads/2021/10/20201113070727-vet-ringneck-blue-petee-1.webp', 
	0, 'IR001', NULL, NULL),
	('https://yeuchim.net/wp-content/uploads/2023/08/vet-duoi-dai-an-do.jpg', 
	0, 'IR001', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/1-3.jpg', 
	1, 'IR002', NULL, NULL),
	('https://vetcanh.com/wp-content/uploads/2021/10/ringneck-2.jpg-1.webp',
	0, 'IR002', NULL, NULL),
	('https://tnavianrescue.org/wp-content/uploads/DSC_0036-scaled.jpg', 
	0, 'IR002', NULL, NULL),

	('https://vetcanh.com/wp-content/uploads/2021/10/e23feccc6ffda6a3ffec.jpg', 
	1, 'XA001', NULL, NULL),
	('https://infofinance.vn/wp-content/uploads/2023/05/gia-1-con-vet-biet-noi.jpg', 
	0, 'XA001', NULL, NULL),
	('https://bizweb.dktcdn.net/100/326/708/articles/indian-ringneck.jpg?v=1615735965647', 
	0, 'XA001', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/Vet-African-Grey-2.jpg', 
	1, 'CP101', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Psittacus_erithacus_-perching_on_tray-8d.jpg/1200px-Psittacus_erithacus_-perching_on_tray-8d.jpg', 
	0, 'CP101', NULL, NULL),
	('https://vetcanh.com/wp-content/uploads/2021/10/dong-luc-vet-1024x683-1.jpg', 
	0, 'CP101', NULL, NULL),

	('https://petkeen.com/wp-content/uploads/2021/09/Cape-parrot-on-top-of-cage_JillLang_Shutterstock.jpg', 
	1, 'CP201', NULL, NULL),
	('https://cdn.sci.news/images/enlarge2/image_3127_1e-Cape-Parrot.jpg', 
	0, 'CP201', NULL, NULL),
	('https://cdn-fastly.petguide.com/media/2022/02/16/8235256/cape-parrot.jpg?size=720x845&nocrop=1', 
	0, 'CP201', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/vet-senegal.jpg', 
	1, 'CP301', NULL, NULL),
	('https://vuongquocvet.com/wp-content/uploads/2021/08/Senegal-2.jpg', 
	0, 'CP301', NULL, NULL),
	('https://vuongquocvet.com/wp-content/uploads/2021/08/Senegal-4.jpg', 
	0, 'CP301', NULL, NULL),

	('https://cdn.download.ams.birds.cornell.edu/api/v1/asset/414494251/', 
	1, 'CP401', NULL, NULL),
	('https://cdn.download.ams.birds.cornell.edu/api/v1/asset/197044941/', 
	0, 'CP401', NULL, NULL),
	('https://i.pinimg.com/1200x/30/d5/45/30d5454bb0decb9e74142e603b1e5f1b.jpg', 
	0, 'CP401', NULL, NULL),

	('https://bizweb.dktcdn.net/thumb/grande/100/326/708/articles/lovebird.jpg?v=1616902386747', 
	1, 'CP501', NULL, NULL),
	('https://cdn.wikifarm.vn/2023/04/vet-lovebird-8.jpg',
	0, 'CP501', NULL, NULL),
	('https://my-pet.vn/wp-content/uploads/2022/12/vet-Lovebird-1.jpg', 
	0, 'CP501', NULL, NULL),

	('https://image.bnews.vn/MediaUpload/Org/2016/10/03/155908_vet-xam-chau-phi.jpg', 
	1, 'CP601', NULL, NULL),
	('https://vcdn-vnexpress.vnecdn.net/2020/09/29/Swearing-parrot-Billy-3410-160-3035-4175-1601367443.jpg', 
	0, 'CP601', NULL, NULL),
	('https://scontent.fsgn8-4.fna.fbcdn.net/v/t1.6435-9/122119811_1334076986941333_1776507504325160922_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=dd63ad&_nc_ohc=i9MhujwyUI4AX9r8xuE&_nc_ht=scontent.fsgn8-4.fna&oh=00_AfCKLUi4sGGOSdwl_r8vdbph-SdJGsn4s3gi2mOr8apbQg&oe=658AF803', 
	0, 'CP601', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/vet-orange-winged-amazon.jpg', 
	1, 'WA301', NULL, NULL),
	('https://petmeshop.com/wp-content/uploads/2020/09/vet-orange-winged-amazon-1-1.jpg', 
	0, 'WA301', NULL, NULL),
	('https://vuongquocvet.com/wp-content/uploads/2021/08/Orange-Winged-Amazon-2.jpg', 
	0, 'WA301', NULL, NULL),

	('https://bizweb.dktcdn.net/100/326/708/files/blue-fronted-amazon-1-6bde88b3-11f7-49c7-8afb-4535ab69af5e.jpg?v=1614651669559', 
	1, 'BA602', NULL, NULL),
	('https://vetcanh.com/wp-content/uploads/2021/10/bluefronted-amazon-amazona-aestiva-xanthopteryx-260nw-70570000-e1633879092359.jpg', 
	0, 'BA602', NULL, NULL),
	('https://petmeshop.com/wp-content/uploads/2020/09/Blue-fronted-Amazon.jpg', 
	0, 'BA602', NULL, NULL),

	('https://bizweb.dktcdn.net/100/326/708/files/green-cheeked-amazon-parrot-01.jpg?v=1614653776531', 
	1, 'FA303', NULL, NULL),
	('https://cdn.optimal.wiki/chimvet.com/2023/08/red-lored-amazon-1.jpg', 
	0, 'FA303', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/f/f3/Gr%C3%BCnwangenamazone_Kopf.JPG', 
	0, 'FA303', NULL, NULL),

	('https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Amazona_oratrix_-Vancouver_Aquarium-8a-4c.jpg/800px-Amazona_oratrix_-Vancouver_Aquarium-8a-4c.jpg', 
	1, 'HA104', NULL, NULL),
	('https://petmeshop.com/wp-content/uploads/2020/09/Vet-double-yellow-headed-amazon-3.jpg', 
	0, 'HA104', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/5/57/Portrait_of_Yellow-headed_Amazon_Parrot.jpg', 
	0, 'HA104', NULL, NULL),

	('https://toplist.vn/images/800px/yellow-naped-amazon-136980.jpg', 
	1, 'NA505', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/Amazona_auropalliata_-Roatan_Tropical_Butterfly_Garden-8a.jpg/640px-Amazona_auropalliata_-Roatan_Tropical_Butterfly_Garden-8a.jpg', 
	0, 'NA505', NULL, NULL),
	('https://images.toplist.vn/images/800px/yellow-naped-amazon-926738.jpg', 
	0, 'NA505', NULL, NULL),

	('https://vetcanh.com/wp-content/uploads/2021/10/243242548_195408066061821_7867016909084537485_n.jpg', 
	1, 'CL201', NULL, NULL),
	('https://petmeshop.com/wp-content/uploads/2020/09/Cockatiel-3.jpg', 
	0, 'CL201', NULL, NULL),
	('https://bizweb.dktcdn.net/100/462/524/products/8e4c492e-8956-4e3e-a452-8e5605845ce9-jpeg.jpg?v=1669732195913', 
	0, 'CL201', NULL, NULL),

	('https://vetcanh.com/wp-content/uploads/2021/10/243285012_383512106831596_2029683691081608233_n.jpg', 
	1, 'CW192', NULL, NULL),
    ('https://petmeshop.com/wp-content/uploads/2023/05/cockatiel-special-1.jpg', 
	0, 'CW192', NULL, NULL),
	('https://vetcanh.com/wp-content/uploads/2021/10/245623652_175263331428641_1886273412272427080_n.jpg', 
	0, 'CW192', NULL, NULL),

	('https://bizweb.dktcdn.net/thumb/grande/100/462/524/products/127772d2-3c3a-4b77-8468-93b8d38b0e0d-jpeg.jpg?v=1669193593043', 
	1, 'RL391', NULL, NULL),
	('https://my-pet.vn/wp-content/uploads/2022/12/vet-Lory-1.jpg', 
	0, 'RL391', NULL, NULL),
	('https://petmeshop.com/wp-content/uploads/2020/09/thong-tin-vet-lory-lorikeet-3.jpg', 
	0, 'RL391', NULL, NULL),

	('https://vetcanh.com/wp-content/uploads/2021/10/245540892_1260689057732967_9442119570217108_n.jpg', 
	1, 'RL409', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/a/a7/Eos_bornea_-Taronga_Zoo%2C_Sydney%2C_Australia-8a.jpg', 
	0, 'RL409', NULL, NULL),
	('https://vetcanh.com/wp-content/uploads/2021/10/243227873_240691981364677_4473124352863918096_n.jpg', 
	0, 'RL409', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/Vet-Electus-3.jpg', 
	1, 'VE358', NULL, NULL),
	('https://vuongquocvet.com/wp-content/uploads/2021/08/electus-2.jpg', 
	0, 'VE358', NULL, NULL),
	('https://bizweb.dktcdn.net/100/462/524/products/z4223368083379-c7f4d0b552af817fa1d4b44183ea02b0.jpg?v=1680232389703', 
	0, 'VE358', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/hahns-macaw-1.jpg', 
	1, 'HM350', NULL, NULL),
	('https://www.kellyvillepets.com.au/cdn/shop/products/HahnsMacaw_31f1d318-cc7d-47f8-8d87-7632dc037483_grande.jpg?v=1571573289', 
	0, 'HM350', NULL, NULL),
	('https://www.thesprucepets.com/thmb/TXGwMK4TCrylt4B6Sf8PJtUXAuA=/2121x0/filters:no_upscale():strip_icc()/GettyImages-501591421-5b1187718e1b6e0036e940bf.jpg', 
	0, 'HM350', NULL, NULL),

	('https://cdn.download.ams.birds.cornell.edu/api/v1/asset/188698901/1800', 
	1, 'YC090', NULL, NULL),
	('https://www.thesprucepets.com/thmb/3OmW2Unfefo-xHslN-afINF1_KE=/2212x0/filters:no_upscale():strip_icc()/Primolius_auricollis-5b6283a246e0fb0025f23d79.jpg', 
	0, 'YC090', NULL, NULL),
	('https://cdn.download.ams.birds.cornell.edu/api/v1/asset/356745111/', 
	0, 'YC090', NULL, NULL),

	('https://www.rainforest-alliance.org/wp-content/uploads/2021/06/scarlet-macaw-square-2.jpg.optimal.jpg', 
	1, 'SM808', NULL, NULL),
	('https://lafeber.com/pet-birds/wp-content/uploads/2018/06/Scarlet-Macaw-2.jpg', 
	0, 'SM808', NULL, NULL),
	('https://petmeshop.com/wp-content/uploads/2020/09/vet-scarlet-macaw-2.jpg', 
	0, 'SM808', NULL, NULL),

	('https://upload.wikimedia.org/wikipedia/commons/c/ce/Anodorhynchus_hyacinthinus_-Disney_-Florida-8.jpg', 
	1, 'HM101', NULL, NULL),
	('https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Anodorhynchus_hyacinthinus_-pet_parrot-8a.jpg/220px-Anodorhynchus_hyacinthinus_-pet_parrot-8a.jpg', 
	0, 'HM101', NULL, NULL),
	('https://www.akronzoo.org/sites/default/files/styles/square_large/public/assets/animals/Hyacinth-macaw-main.png?h=00546c34&itok=4PGxtlvE', 
	0, 'HM101', NULL, NULL),

	('https://petmeshop.com/wp-content/uploads/2020/09/vet-Green-Winged-Macaw-avatar-dep.jpg', 
	1, 'GW125', NULL, NULL),
	('https://static.wikia.nocookie.net/naturerules1/images/c/c8/3a2c398337c8af500ddbf3061bdf8274.jpg/revision/latest?cb=20210117003112', 
	0, 'GW125', NULL, NULL),
	('https://www.sfzoo.org/wp-content/uploads/2021/03/img_macaw_mw2_large.jpg', 
	0, 'GW125', NULL, NULL),

	('https://petschoolshop.com/wp-content/uploads/2019/02/vet-xich-baby.jpg', 
	0, NULL, 'BN001', NULL),
	('https://chimcanh.net/wp-content/uploads/2019/04/maxresdefault-1.jpg', 
	0, NULL, 'BN001', NULL),
	('https://bizweb.dktcdn.net/100/462/524/products/z4223156920621-f8b825ebed0b809f1c7c12c927a19a61.jpg?v=1680231999937', 
	1, NULL, 'BN001', NULL),

	('https://s1.storage.5giay.vn/image/2016/11/20161110_d4492fd93903bc52814dce1293f53e1d_1478750192.jpg', 
	0, NULL, 'BN002', NULL),
	('https://bizweb.dktcdn.net/100/462/524/products/e149c111-4684-4e03-98fa-c369909573a2-jpeg.jpg?v=1671080265377', 
	0, NULL, 'BN002', NULL),
	('https://sinhvatkieng.com/wp-content/uploads/2018/07/d9ae800c36497db4ca0855e58e8d90b3.jpeg', 
	1, NULL, 'BN002', NULL)
GO

INSERT INTO [Order]([order_id],[customer],[order_date],[order_status],[name_receiver],[phone_receiver],[address_receiver],[payment_status],[payment_type],[total_price],[cancel_reason])
VALUES('230925O0001','customer','2022-09-25',N'Đã hủy', N'Quân','0912345678',null,N'Đã thanh toán',N'Chuyển khoản',3950000,N'Khách từ chối nhận hàng'),
	  ('230915O0002','giaphong','2023-09-15',N'Đã giao hàng',N'Phong','0368445224',null,N'Đã thanh toán',N'Chuyển khoản',27600000,NULL),
	  ('230916O0003','nhathoang','2023-03-16',N'Đang giao hàng',N'Hoàng','0368445224',null,N'Chưa thanh toán',N'Tiền mặt',4950000,NULL),
	  ('230917O0004','huynhduc','2023-09-17',N'Đang giao hàng',N'Đức','0912245678',null,N'Chưa thanh toán',N'Tiền mặt',710000,NULL),
	  ('230918O0005','khachhangvip','2023-09-18',N'Đã hủy',N'Thuận','0932145678',null,N'Đã thanh toán',N'Chuyển khoản',3450000,N'Khách từ chối nhận hàng'),
	  ('230919O0006','customer','2020-09-19',N'Đã giao hàng',N'Quân','0912345678',null,N'Đã thanh toán',N'Tiền mặt',25570000,NULL),
	  ('230912O0007','customer','2020-09-20',N'Đã đánh giá',N'Quân','0912345678',null,N'Đã thanh toán',N'Chuyển khoản',7650000,NULL),
	  ('231027O0008','customer','2023-6-27',N'Chờ xử lý',N'Minh','0915324678',null,N'Đã thanh toán',N'Chuyển khoản',700000,NULL),
	  ('231027O0009','giaphong','2023-7-27',N'Chờ xử lý',N'Thọ','0955555678',null,N'Đã thanh toán',N'Chuyển khoản',700000,NULL),
	  ('231027O0010','huynhduc','2023-7-27',N'Chờ xử lý',N'Đức','0912245678',null,N'Đã thanh toán',N'Chuyển khoản',700000,NULL),
	  ('231027O0011','nhathoang','2023-7-27',N'Chờ xử lý',N'Hoàng','0368445224',null,N'Đã thanh toán',N'Chuyển khoản',700000,NULL);
GO

INSERT INTO [OrderItem]([order_id],[bird_id],[nest_id],[accessory_id],[unit_price],[order_quantity])
VALUES ('230925O0001','CW192',null,null,3500000,1),
		('230925O0001',null,null,'LM001',450000,1),
		('230915O0002',null,null,'LN001',13800000,2),
		('230916O0003','WA301',null,null,4500000,1),
		('230916O0003',null,null,'LM001',450000,1),
		('230917O0004',null,null,'GT001',120000,2),
		('230917O0004',null,null,'BL001',500000,1),
		('230917O0004',null,null,'CT001',90000,1),
		('230918O0005','CP201',null,null,3000000,1),
		('230918O0005',null,null,'LM001',450000,1),
		('230919O0006','YC090',null,null,25000000,1),
		('230919O0006',null,null,'LM001',450000,1),
		('230919O0006',null,null,'NK200',120000,1),
		('230912O0007','XT001',null,null,6500000,1),
	    ('230912O0007',null,null,'LM001',450000,1),
		('230912O0007',null,null,'GA001',600000,1),
		('230912O0007',null,null,'KC213',100000,1),
		('231027O0008',null,null,'GA001',600000,1),
		('231027O0008',null,null,'KC213',100000,1),
		('231027O0009',null,null,'GA001',600000,1),
		('231027O0009',null,null,'KC213',100000,1),
		('231027O0010',null,null,'GA001',600000,1),
		('231027O0010',null,null,'KC213',100000,1),
		('231027O0011',null,null,'GA001',600000,1),
		('231027O0011',null,null,'KC213',100000,1)
GO

INSERT INTO [Feedback]([customer],[order_item_id],[rating],[comment],[feedback_date]) 
VALUES('customer',1,5,N'Chất lượng ok. Đc tặng cả cái đổ thức ăn. Giao nhanh. Cảm ơn ship và shop','2023-05-23'),
	  ('customer',2,5,N'Sản phẩm OK.giao hàng hơi chậm.rất vừa với lồng và tiện lợi.mọi người nên mua cho mình 1 cái vừa đỡ mất thời gian vừa nhàn','2023-05-23'),
	  ('giaphong',3,5,N'Sản phẩm OK.giao hàng hơi chậm.rất vừa với lồng và tiện lợi','2023-05-24'),
	  ('nhathoang',4,5,N'Sản phẩm OK.Chim rất đẹp. Giao hàng nhanh','2023-05-24'),
	  ('nhathoang',5,5,N'Sản phẩm OK. Lồng rất vừa, cứng cáp, chất lượng tuyệt vời','2023-05-24'),
	  ('huynhduc',6,4,N'Mua lần 2 ở shop. Rất hài lòng','2023-05-06'),
	  ('huynhduc',7,4,N'Mua lần 2 ở shop. Rất hài lòng','2023-05-06'),
	  ('huynhduc',8,4,N'Mua lần 2 ở shop. Rất hài lòng','2023-05-06'),
	  ('khachhangvip',9,4,N'Giao đúng mẫu đẹp ,ok','2023-05-28'),
	  ('khachhangvip',10,4,N'Nhận được hàng rồi.đẹp quá.rất cứng cáp.sẽ mua thêm','2023-05-28'),
	  ('customer',11,5,N'Shop giao hàng rất nhanh mặc dù là ngày gần tết. Chim rất ok','2023-06-12'),
	  ('customer',12,5,N'sản phẩm ok ship nhanh hơn hẹn','2023-06-12'),
	  ('customer',13,5,N'sản phẩm ok ship nhanh hơn hẹn','2023-06-12'),
	  ('customer',14,5,N'Shop giao hàng rất nhanh mặc dù là ngày gần tết. Chim rất ok','2023-07-23'),
	  ('customer',15,5,N'sản phẩm ok, khung rất cứng cấp, chắc chắn, mẫu hình bắt mắt','2023-07-23'),
	  ('customer',16,5,N'rất ưng ý','2023-07-23'),
	  ('customer',17,5,N'Giao hàng nhanh. Đóng gói chắc chắn.','2023-07-23');
GO

CREATE VIEW [View_Feedback] AS
SELECT u.full_name,u.email,f.rating,f.comment,f.feedback_date,o.bird_id,b.bird_name,a.accessory_id,a.accessory_name,i.image_url 
FROM [USER] u
RIGHT JOIN [Feedback] f
ON u.username = f.customer
LEFT JOIN [OrderItem] o
ON f.order_item_id = o.order_item_id
LEFT JOIN [Bird] b
ON o.bird_id = b.bird_id
LEFT JOIN [Accessory] a
ON o.accessory_id = a.accessory_id
LEFT JOIN [Image] i 
ON b.bird_id = i.bird_id OR a.accessory_id = i.accessory_id
WHERE i.is_thumbnail = 1
GO
