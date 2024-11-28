-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        11.5.2-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- lcms 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `lcms`;
CREATE DATABASE IF NOT EXISTS `lcms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `lcms`;

-- 테이블 lcms.fw_jdbclog_exception 구조 내보내기
DROP TABLE IF EXISTS `fw_jdbclog_exception`;
CREATE TABLE IF NOT EXISTS `fw_jdbclog_exception` (
`EVENT_ID` bigint(20) NOT NULL,
`I` smallint(6) NOT NULL,
`GUID` varchar(254) NOT NULL,
`TRACE_LINE` text DEFAULT NULL,
PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.fw_jdbclog_exception:~0 rows (대략적) 내보내기

-- 테이블 lcms.fw_jdbclog_info 구조 내보내기
DROP TABLE IF EXISTS `fw_jdbclog_info`;
CREATE TABLE IF NOT EXISTS `fw_jdbclog_info` (
`EVENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
`GUID` varchar(254) NOT NULL,
`TIMESTMP` timestamp NOT NULL,
`DATE` varchar(15) NOT NULL,
`FORMATTED_MESSAGE` text NOT NULL,
`LOGGER_NAME` varchar(254) NOT NULL,
`LEVEL_STRING` varchar(254) NOT NULL,
`THREAD_NAME` varchar(254) DEFAULT NULL,
`REFERENCE_FLAG` smallint(6) DEFAULT NULL,
`CALLER_FILENAME` varchar(254) NOT NULL,
`CALLER_CLASS` varchar(254) NOT NULL,
`CALLER_METHOD` varchar(254) NOT NULL,
`CALLER_LINE` char(4) NOT NULL,
`ARG0` varchar(254) DEFAULT NULL,
`ARG1` varchar(254) DEFAULT NULL,
`ARG2` varchar(254) DEFAULT NULL,
`ARG3` varchar(254) DEFAULT NULL,
PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3899 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='JDBC로그 테이블';

-- 테이블 데이터 lcms.fw_jdbclog_info:~0 rows (대략적) 내보내기

-- 테이블 lcms.fw_jdbclog_property 구조 내보내기
DROP TABLE IF EXISTS `fw_jdbclog_property`;
CREATE TABLE IF NOT EXISTS `fw_jdbclog_property` (
`EVENT_ID` bigint(20) NOT NULL,
`MAPPED_KEY` varchar(254) NOT NULL,
`MAPPED_VALUE` text DEFAULT NULL,
PRIMARY KEY (`EVENT_ID`,`MAPPED_KEY`),
CONSTRAINT `FW_JDBCLOG_PROPERTY_ibfk_1` FOREIGN KEY (`EVENT_ID`) REFERENCES `fw_jdbclog_info` (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.fw_jdbclog_property:~0 rows (대략적) 내보내기

-- 테이블 lcms.refresh_token 구조 내보내기
DROP TABLE IF EXISTS `refresh_token`;
CREATE TABLE IF NOT EXISTS `refresh_token` (
`id` bigint(20) NOT NULL,
`expiry_date` datetime(6) NOT NULL,
`revoked` bit(1) NOT NULL,
`token` varchar(255) NOT NULL,
`user_id` bigint(20) DEFAULT NULL,
PRIMARY KEY (`id`),
UNIQUE KEY `UK_r4k4edos30bx9neoq81mdvwph` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.refresh_token:~0 rows (대략적) 내보내기

-- 테이블 lcms.refresh_token_seq 구조 내보내기
DROP TABLE IF EXISTS `refresh_token_seq`;
CREATE TABLE IF NOT EXISTS `refresh_token_seq` (
`next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.refresh_token_seq:~1 rows (대략적) 내보내기
INSERT INTO `refresh_token_seq` (`next_val`) VALUES
(1);

-- 테이블 lcms.tb_board 구조 내보내기
DROP TABLE IF EXISTS `tb_board`;
CREATE TABLE IF NOT EXISTS `tb_board` (
`ROW_TYPE` bigint(20) NOT NULL AUTO_INCREMENT,
`TITLE` varchar(100) DEFAULT NULL,
`REG_ID` varchar(100) DEFAULT NULL,
`POST_ID` varchar(100) DEFAULT NULL,
`CONTENTS` varchar(1000) DEFAULT NULL,
`COMMUNITY_ID` varchar(100) DEFAULT NULL,
`HIT_COUNT` varchar(100) DEFAULT NULL,
`REG_DATE` date DEFAULT NULL,
`IS_NOTICE` varchar(2) DEFAULT NULL,
UNIQUE KEY `TB_BOARD_pk` (`ROW_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='넥사크로 샘플 TB_BOARD';

-- 테이블 데이터 lcms.tb_board:~2 rows (대략적) 내보내기
INSERT INTO `tb_board` (`ROW_TYPE`, `TITLE`, `REG_ID`, `POST_ID`, `CONTENTS`, `COMMUNITY_ID`, `HIT_COUNT`, `REG_DATE`, `IS_NOTICE`) VALUES
(5, '제목1', NULL, NULL, '내용1', NULL, NULL, NULL, NULL),
(6, '제목2', NULL, NULL, '내용2', NULL, NULL, NULL, NULL);

-- 테이블 lcms.tb_dept 구조 내보내기
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE IF NOT EXISTS `tb_dept` (
`ROW_TYPE` bigint(20) NOT NULL AUTO_INCREMENT,
`deptId` int(11) DEFAULT NULL,
`deptName` varchar(100) DEFAULT NULL,
`mmberCount` varchar(100) DEFAULT NULL,
PRIMARY KEY (`ROW_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='넥사크로 샘플 TB_Dept';

-- 테이블 데이터 lcms.tb_dept:~0 rows (대략적) 내보내기

-- 테이블 lcms.tb_menu 구조 내보내기
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE IF NOT EXISTS `tb_menu` (
`ROW_TYPE` bigint(20) NOT NULL AUTO_INCREMENT,
`code` varchar(50) DEFAULT NULL,
`name` varchar(50) DEFAULT NULL,
`depth` int(11) DEFAULT NULL,
`path` varchar(100) DEFAULT NULL,
PRIMARY KEY (`ROW_TYPE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.tb_menu:~24 rows (대략적) 내보내기
INSERT INTO `tb_menu` (`ROW_TYPE`, `code`, `name`, `depth`, `path`) VALUES
(1, 'admin1', '어드민1', 0, '/v1/admin/1'),
(2, 'admin1_1', '어드민1_1', 1, '/v1/admin/1_1'),
(3, 'admin1_2', '어드민1_2', 1, '/v1/admin/1_2'),
(4, 'admin1_3', '어드민1_3', 1, '/v1/admin/1_3'),
(5, 'admin2', '어드민2', 0, '/v1/admin/2'),
(6, 'admin3', '어드민3', 0, '/v1/admin/1'),
(7, 'admin3_1', '어드민3_1', 1, '/v1/admin/3_1'),
(8, 'admin2_1', '어드민2_1', 1, '/v1/admin/2_1'),
(9, 'admin2_2', '어드민2_2', 1, '/v1/admin/2_2'),
(10, 'manager1', '매니저1', 0, '/v1/manager/1'),
(11, 'manager2', '매니저2', 0, '/v1/manager/2'),
(12, 'manager3', '매니저3', 0, '/v1/manager/3'),
(13, 'user1', '사용자1', 0, '/v1/user/1'),
(14, 'user2', '사용자2', 0, '/v1/user/2'),
(15, 'user3', '사용자3', 0, '/v1/user/3'),
(16, 'user3_1', '사용자3_1', 1, '/v1/user/3_1'),
(17, 'user3_2', '사용자3_2', 1, '/v1/user/3_2'),
(18, 'user3_3', '사용자3_3', 1, '/v1/user/3_3'),
(19, 'user1_1', '사용자1_1', 1, '/v1/user/1_1'),
(20, 'user1_2', '사용자1_2', 1, '/v1/user/1_2'),
(21, 'user1_3', '사용자1_3', 1, '/v1/user/1_3'),
(22, 'user2_1', '사용자2_1', 1, '/v1/user/2_1'),
(23, 'user2_2', '사용자2_2', 1, '/v1/user/2_2'),
(24, 'user2_3', '사용자2_3', 1, '/v1/user/2_3');

-- 테이블 lcms.tb_menu_group 구조 내보내기
DROP TABLE IF EXISTS `tb_menu_group`;
CREATE TABLE IF NOT EXISTS `tb_menu_group` (
`ROW_TYPE` bigint(20) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
`menu_row_type` bigint(20) NOT NULL,
PRIMARY KEY (`ROW_TYPE`),
UNIQUE KEY `name_menu_row_type` (`name`,`menu_row_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.tb_menu_group:~3 rows (대략적) 내보내기
INSERT INTO `tb_menu_group` (`ROW_TYPE`, `name`, `menu_row_type`) VALUES
(1, '어드민그룹1', 1),
(2, '어드민그룹1', 2),
(3, '어드민그룹1', 3);

-- 테이블 lcms.tb_refresh_token 구조 내보내기
DROP TABLE IF EXISTS `tb_refresh_token`;
CREATE TABLE IF NOT EXISTS `tb_refresh_token` (
`row_type` bigint(20) NOT NULL AUTO_INCREMENT,
`token` varchar(50) DEFAULT '0',
`user_row_type` bigint(20) NOT NULL,
`expiry_date` datetime DEFAULT NULL,
`id` bigint(20) NOT NULL,
`revoked` bit(1) NOT NULL,
PRIMARY KEY (`row_type`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.tb_refresh_token:~14 rows (대략적) 내보내기
INSERT INTO `tb_refresh_token` (`row_type`, `token`, `user_row_type`, `expiry_date`, `id`, `revoked`) VALUES
(1, 'N2UxNWExOTAtMjgwMC00MWIzLWJmM2MtYTc1MWI5OGQ3N2Q0', 1, '2024-12-12 06:52:05', 1, b'0'),
(2, 'OTk4YjA5ZjAtN2ZiMi00MGQxLTk1ZjQtMTRhNzM0YTQxYzg4', 2, '2024-12-12 06:56:42', 2, b'0'),
(3, 'Njg2Y2QxOGYtM2YxZC00NmU0LWE1MjUtY2RmNThlMDlhOWM1', 3, '2024-12-12 06:56:54', 3, b'0'),
(4, 'OTMxNjE4NjItZjljZi00YjcyLTkwZTktMDY4OTNlNTJkYTUx', 3, '2024-12-12 06:59:00', 4, b'0'),
(5, 'MDUyODkwYzUtNWUwYi00ODI1LTg5OWMtNGY2Y2QxYTE5NDYw', 3, '2024-12-12 07:33:59', 52, b'0'),
(6, 'MGRiMGFjMjMtZDFmZC00Y2Y1LWIyMTUtNzA2NmI0ZjZiYWFm', 1, '2024-12-12 07:34:57', 53, b'0'),
(7, 'MjUyZTVlNWYtNGMzOC00OWZmLWE3N2QtMTliNzJiYTk3NGU3', 1, '2024-12-12 07:53:27', 102, b'0'),
(8, 'NjZkMTlmNjAtN2MwYS00ZTRiLTk5M2QtNjZiNmU0ZDZiNzli', 1, '2024-12-12 07:54:04', 103, b'0'),
(9, 'MDgyOTQ2YzYtMWQ2Yy00NTIyLTg0ZDEtNmYxYmU5MWJmMTg2', 1, '2024-12-12 07:55:03', 104, b'0'),
(10, 'Y2Y3MTdhYzQtMDU2ZS00NzVhLWE0MGEtZTEyNjJlN2M5MTBl', 3, '2024-12-12 08:03:45', 152, b'0'),
(11, 'ZWJlNzZkYzUtMzQ2YS00ODg2LWI2NjItOTgzMWI2Yzg0M2M5', 1, '2024-12-12 08:13:06', 153, b'0'),
(12, 'Yzg2M2UxMzAtMDMxMS00YjVhLTk1NjEtOTczOGJiZWY4NjQy', 1, '2024-12-13 04:52:44', 202, b'0'),
(13, 'MGZkYTE4NzctNTdjNy00YzhlLWIwZjQtMmMzNjExYTcyMDlk', 1, '2024-12-13 07:55:01', 252, b'0'),
(14, 'MDkyMGVjMzktYzMzYy00M2M2LTk0OWItODUzYmZiMjVhZmZi', 1, '2024-12-13 08:10:39', 253, b'0'),
(15, 'ZTc4OTk2MjQtMTRhZi00ZDMwLTk1YTctMzIzOGJiNTgxYmMy', 1, '2024-12-13 08:33:21', 302, b'0');

-- 테이블 lcms.tb_refresh_token_seq 구조 내보내기
DROP TABLE IF EXISTS `tb_refresh_token_seq`;
CREATE TABLE IF NOT EXISTS `tb_refresh_token_seq` (
`next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.tb_refresh_token_seq:~1 rows (대략적) 내보내기
INSERT INTO `tb_refresh_token_seq` (`next_val`) VALUES
(401);

-- 테이블 lcms.tb_role 구조 내보내기
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE IF NOT EXISTS `tb_role` (
`ROW_TYPE` bigint(20) NOT NULL AUTO_INCREMENT,
`code` varchar(50) NOT NULL DEFAULT 'ROLE_USER',
`name` varchar(50) NOT NULL DEFAULT '사용자',
PRIMARY KEY (`ROW_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.tb_role:~0 rows (대략적) 내보내기

-- 테이블 lcms.tb_user 구조 내보내기
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE IF NOT EXISTS `tb_user` (
`RAW_TYPE` bigint(20) NOT NULL AUTO_INCREMENT,
`USER_ID` varchar(100) DEFAULT NULL,
`USER_NAME` varchar(100) DEFAULT NULL,
`EN_NAME` varchar(100) DEFAULT NULL,
`PASSWORD` varchar(100) DEFAULT NULL,
`EMAIL` varchar(100) DEFAULT NULL,
`COMP_PHONE` varchar(100) DEFAULT NULL,
`PHONE` varchar(100) DEFAULT NULL,
`CELL_PHONE` varchar(100) DEFAULT NULL,
`COMPANY` varchar(100) DEFAULT NULL,
`JOB_POSITION` varchar(100) DEFAULT NULL,
`ASSIGNMENT` varchar(100) DEFAULT NULL,
`OFFICER_YN` varchar(100) DEFAULT NULL,
`FAX` varchar(100) DEFAULT NULL,
`ZIP_CODE` varchar(100) DEFAULT NULL,
`ADDRESS` varchar(100) DEFAULT NULL,
`COMP_ZIP_CODE` varchar(100) DEFAULT NULL,
`COMP_ADDRESS` varchar(100) DEFAULT NULL,
`DEPT_ID` varchar(100) DEFAULT NULL,
`role` enum('ADMIN','MANAGER','USER') DEFAULT NULL,
`id` bigint(20) NOT NULL,
`menu_group_row_type` bigint(20) NOT NULL,
`menu_group_name` varchar(50) DEFAULT NULL,
PRIMARY KEY (`RAW_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='넥사크로 샘플 TB_USER';

-- 테이블 데이터 lcms.tb_user:~3 rows (대략적) 내보내기
INSERT INTO `tb_user` (`RAW_TYPE`, `USER_ID`, `USER_NAME`, `EN_NAME`, `PASSWORD`, `EMAIL`, `COMP_PHONE`, `PHONE`, `CELL_PHONE`, `COMPANY`, `JOB_POSITION`, `ASSIGNMENT`, `OFFICER_YN`, `FAX`, `ZIP_CODE`, `ADDRESS`, `COMP_ZIP_CODE`, `COMP_ADDRESS`, `DEPT_ID`, `role`, `id`, `menu_group_row_type`, `menu_group_name`) VALUES
(3, 'kbdata', '국민', '큽큽', '$2a$10$Qcz3PnoEt1Cgp4XzF4dOhOeIYMigKOppAaJaTy2yQSy.Sv5P8nIMS', 'admin@mail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ADMIN', 1, 0, '어드민그룹1'),
(4, 'kbdsm', '국민매니저', '큽큽', '$2a$10$D60QKjiSfuxc9mpQJVqjQePXA5X.HlhoARANejJxRr43g8VSw.4jW', 'manager@mail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'MANAGER', 2, 0, NULL),
(5, 'kbdsu', '국민유저', '큽큽', '$2a$10$ALu5TD8Xq87kU4srjkdMMOJeh8jItSxlV9FhK4gCdlmw6naknzweq', 'user@mail.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'USER', 3, 0, NULL);

-- 테이블 lcms.tb_user_seq 구조 내보내기
DROP TABLE IF EXISTS `tb_user_seq`;
CREATE TABLE IF NOT EXISTS `tb_user_seq` (
`next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 lcms.tb_user_seq:~1 rows (대략적) 내보내기
INSERT INTO `tb_user_seq` (`next_val`) VALUES
(101);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
