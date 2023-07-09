<html>
<head>
    <title>Страница регистрации</title>
</head>
<body>
    <form method="post">
       Name:<label for="idName">
            <input type="text" name="name" id="idName">
        </label><br>

        Birthday<label for="birthId">
            <input type="date" name="birthday" id="birthId">
        </label><br>

        Country:<label for="idCountry">
            <input type="text" name="country" id="idCountry">
        </label><br>

        Email:<label for="idEmail">
            <input type="text" name="email" id="idEmail" required>
        </label><br>

        Password:<label for="idPassword">
            <input type="password" name="password" id="idPassword" required>
        </label><br>

        Role:<label>
            <select name="role">
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </label><br>

        Sex:<label>
            <select name="gender">
                <option value="MALE">MALE</option>
                <option value="FEMALE">FEMALE</option>
            </select>
        </label><br>

       <button type="submit">Registration</button>
    </form>

</body>
</html>