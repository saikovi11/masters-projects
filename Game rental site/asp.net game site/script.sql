USE [master]
GO
/****** Object:  Database [Game]    Script Date: 06/27/16 8:57:50 PM ******/
CREATE DATABASE [Game]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Game', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Game.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Game_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Game_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Game] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Game].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Game] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Game] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Game] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Game] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Game] SET ARITHABORT OFF 
GO
ALTER DATABASE [Game] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Game] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Game] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Game] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Game] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Game] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Game] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Game] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Game] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Game] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Game] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Game] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Game] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Game] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Game] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Game] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Game] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Game] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Game] SET  MULTI_USER 
GO
ALTER DATABASE [Game] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Game] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Game] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Game] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Game] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Game]
GO
/****** Object:  Table [dbo].[tblGame]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblGame](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](50) NULL,
	[Descrption] [nvarchar](max) NULL,
	[Image] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblMotel] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Phone] [nvarchar](50) NULL,
	[Email] [nvarchar](50) NULL,
	[Password] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[tblGame] ON 

INSERT [dbo].[tblGame] ([Id], [Title], [Descrption], [Image]) VALUES (1, N'Warcraft', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', N'img/Battle_Realms.jpg')
INSERT [dbo].[tblGame] ([Id], [Title], [Descrption], [Image]) VALUES (2, N'BattleRealms', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', N'img/Battle_Realms.jpg')
INSERT [dbo].[tblGame] ([Id], [Title], [Descrption], [Image]) VALUES (5, N'GoneHome', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', N'img/Battle_Realms.jpg')
INSERT [dbo].[tblGame] ([Id], [Title], [Descrption], [Image]) VALUES (6, N'Battlefield4', N'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum', N'img/Battle_Realms.jpg')
SET IDENTITY_INSERT [dbo].[tblGame] OFF
SET IDENTITY_INSERT [dbo].[tblUser] ON 

INSERT [dbo].[tblUser] ([Id], [Name], [Phone], [Email], [Password]) VALUES (1, N'piyush sardhara', N'9276884234', N'sardharapiyush7@gmail.com', N'123')
SET IDENTITY_INSERT [dbo].[tblUser] OFF
/****** Object:  StoredProcedure [dbo].[usp_tblGame_Select]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[usp_tblGame_Select] 


AS
BEGIN
			
				Select * from [dbo].[tblGame]
			
END

GO
/****** Object:  StoredProcedure [dbo].[usp_tblGame_SelectByID]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREate PROCEDURE [dbo].[usp_tblGame_SelectByID] 

@ID Int

AS
BEGIN
			
				Select * from [dbo].[tblGame] Where Id=@ID
			
END

GO
/****** Object:  StoredProcedure [dbo].[usp_tblUser_Insert]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create PROCEDURE [dbo].[usp_tblUser_Insert]


@name nvarchar(50),
@phone nvarchar(50),
@email nvarchar(50),
@password nvarchar(50)

AS
BEGIN

              IF NOT EXISTS(SELECT ID FROM [dbo].[tblUser] WHERE Email = @email)
			  BEGIN

			          INSERT INTO [dbo].[tblUser]
					  (
							[Name],
							[Phone],
							[Email],
							[Password]


					 )
					  VALUES
					  (
							@name,
							@phone,
							@email,
							@password
					  )
			          SELECT SCOPE_IDENTITY()
			  END
			  ELSE
			  BEGIN
			          SELECT -1
			  END

END



GO
/****** Object:  StoredProcedure [dbo].[usp_tblUser_Login]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[usp_tblUser_Login] 

@email nvarchar(50),
@password nvarchar(50)  

AS
BEGIN
			IF EXISTS(SELECT [ID] FROM [dbo].[tblUser] WHERE Email = @email)
			BEGIN
				Select
						[Id],
						[Name],
						[Phone],
						[Email]
						
				FROM [dbo].[tblUser] WHERE Email = @email AND Password=@Password
			END
END






GO
/****** Object:  StoredProcedure [dbo].[usp_tblUser_Select]    Script Date: 06/27/16 8:57:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[usp_tblUser_Select] 

@email nvarchar(50)

AS
BEGIN
			IF EXISTS(SELECT [Id] FROM [dbo].[tblUser] WHERE Email = @email)
			BEGIN
				Select
						
					Id,
					Name,
					Phone,
					Email,
					Password

				FROM [dbo].[tblUser] WHERE Email = @email  COLLATE SQL_Latin1_General_CP1_CS_AS
			END
END

GO
USE [master]
GO
ALTER DATABASE [Game] SET  READ_WRITE 
GO
