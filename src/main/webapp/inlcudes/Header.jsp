<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        * {
	       margin: 0;
	       padding: 0;
	       font-family: Arial, sans-serif;
	       box-sizing: border-box;
   	 	}
        header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 80px;
            z-index: 1000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: linear-gradient(to right, #EF9651, #EC5228);
            padding: 10px 30px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .logo img {
            height: 60px;
            width: auto;
        }

        nav ul {
            list-style: none;
            display: flex;
            gap: 30px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }
        
        /* Add this to your existing styles */
		.icon-btn {
		    background: none;
		    border: none;
		    color: white;
		    padding: 8px;
		    cursor: pointer;
		    transition: all 0.3s ease;
		    font-size: 1.2rem;
		}
		
		/* Cart icon specific styles */
		.cart-btn .fa-shopping-cart {
		    color: white;
		}
		
		.cart-btn:hover .fa-shopping-cart {
		    color: #FFD700; /* Gold color */
		    transform: scale(1.1);
		}
		
		/* Logout icon specific styles */
		.logout-btn .fa-sign-out-alt {
		    color: white;
		}
		
		.logout-btn:hover .fa-sign-out-alt {
		    color: #FF4444; /* Red color */
		    transform: scale(1.1);
		}
		
		/* Update existing icons class */
		.icons {
		    display: flex;
		    gap: 15px; /* Reduced gap for better spacing */
		    align-items: center;
		}
    
   	</style>
</head>
<body>
	<header>
	    <div class="logo">
            <img src="../images/logo.png" alt="logo">
        </div>
        <nav>
            <ul>
                <li><a href="Home.jsp">Home</a></li>
                <li><a href="Menu.jsp">Menu</a></li>
                <li><a href="AboutUs.jsp">About Us</a></li>
            </ul>
        </nav>
		<div class="icons">
		    <!-- Cart Icon -->
		    <section class="icon">
		        <a href="Cart.jsp">
		            <button type="button" class="icon-btn cart-btn">
		                <i class="fas fa-shopping-cart"></i>
		            </button>
		        </a>
		    </section>
		
		    <!-- Profile Icon -->
		    <section class="icon">
		        <a href="<%= request.getContextPath() + "/pages/UserProfile.jsp" %>">
		            <button type="button" class="icon-btn profile-btn">
		                <i class="fas fa-user-circle"></i>
		            </button>
		        </a>
		    </section>
		
		    <!-- Logout Icon -->
		    <section class="icon">
		        <form action="<%=request.getContextPath()%>/LogoutServlet" method="post">
		            <button type="submit" class="icon-btn logout-btn">
		                <i class="fas fa-sign-out-alt"></i>
		            </button>
		        </form>
		    </section>
		</div>
	</header>
</body>
 