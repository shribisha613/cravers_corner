<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Website Header</title>
    
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        /* Remove default margin and padding */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Header Styles */
        header {
            background-color: #D46F2D; /* Dark Orange */
            color: #fff;
            height: 80px; /* Reduced height */
            padding: 0 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
        }

        /* Logo */
        .logo img {
            height: 50px; /* Logo height */
            width: auto;
        }

        /* Navigation Icons */
        .nav-icons {
            display: flex;
            gap: 20px;
            align-items: center;
        }

		     .icon {
		    width: 40px;
		    height: 40px;
		    border-radius: 50%;
		    background-color: #fff;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    cursor: pointer;
		    transition: background-color 0.3s ease;
		    border: none;
		    padding: 0;
		}
        .icon img {
            width: 100%;
    height: 100%;
    border-radius: 50%;
    object-fit: cover;
        }

			       .icon:hover {
			    background-color: #f1a500;
			}
			
			/* Logout button specific override */
			.logout-btn {
			    background-color: #fff;
			}

    </style>
</head>
<body>

    <!-- Header -->
    <header>
        <!-- Logo -->
        <div class="logo">
            <img src="images/logo-placeholder.png" alt="Logo"> <!-- Placeholder for logo -->
        </div>

        <!-- Navigation Icons -->
        <div class="nav-icons">
				<a href="<%=request.getContextPath()%>/pages/UserProfile.jsp">
				    <div class="icon">
				        <img src="<%=request.getContextPath()%>/images/food2.jpg" alt="Profile"> <!-- Profile Icon -->
				    </div>
				</a>
	            <form action="<%=request.getContextPath()%>/LogoutServlet" method="post" style="display: inline;">
			    <button type="submit" class="icon logout-btn">
			        <i class="fas fa-sign-out-alt"></i>
			    </button>
			</form>
        </div>
    </header>

</body>
</html>
