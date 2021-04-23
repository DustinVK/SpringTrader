    <tr th:each="user : ${users}">
                    <td th:text="${user.username}"></td>
                </tr>