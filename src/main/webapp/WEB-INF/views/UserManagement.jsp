<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Test App</title>
        <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
    </head>
    <body data-ng-app="myApp" class="ng-cloak">
        <div class="generic-container" data-ng-controller="UserController as controller">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="lead">User Registration Form </span></div>
                <div class="formcontainer">
                    <form data-ng-submit="controller.submit()" name="myForm" class="form-horizontal">
                        <input type="hidden" data-ng-model="controller.user.id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="uname">Name</label>
                                <div class="col-md-7">
                                    <input type="text" data-ng-model="controller.user.userName" id="uname" class="username form-control input-sm"
                                        placeholder="Enter your name" required data-ng-minlength="3"/>
                                    <div class="has-error" data-ng-show="myForm.$dirty">
                                        <span data-ng-show="myForm.uname.$error.required">This is a required field</span>
                                        <span data-ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                        <span data-ng-show="myForm.uname.$invalid">This field is invalid </span>
                                     </div>
                                </div>

                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="address">Address</label>
                                <div class="col-md-7">
                                    <input type="text" data-ng-model="controller.user.address" id="address" class="form-control input-sm"
                                        placeholder="Enter your Address. [This field is validation free]"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="email">Email</label>
                                <div class="col-md-7">
                                    <input type="email" data-ng-model="controller.user.email" id="email" class="email form-control input-sm"
                                        placeholder="Enter your Email" required/>
                                    <div class="has-error" data-ng-show="myForm.$dirty">
                                        <span data-ng-show="myForm.email.$error.required">This is a required field</span>
                                        <span data-ng-show="myForm.email.$invalid">This field is invalid </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
                                <input type="submit" id="submit" value="{{!controller.user.id ? 'Add' : 'Update'}}"
                                    class="btn btn-primary btn-sm" data-ng-disabled="myForm.$invalid">
                                <button type="button" id="reset" data-ng-click="controller.reset()" class="btn btn-warning btn-sm"
                                    data-ng-disabled="myForm.$pristine">Reset Form</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading"><span class="lead">List of Users </span></div>
                <div class="tablecontainer">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Address</th>
                                <th>Email</th>
                                <th width="20%"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr data-ng-repeat="u in controller.users" class="user-row">
                                <td><span data-ng-bind="u.userName" class="user-name-td"></span></td>
                                <td><span data-ng-bind="u.address"></span></td>
                                <td><span data-ng-bind="u.email"></span></td>
                                <td>
                                    <button type="button" id="edit" data-ng-click="controller.edit(u.id)"
                                        class="btn btn-success custom-width">Edit</button>
                                    <button type="button" id="remove" data-ng-click="controller.openModal(u)"
                                        class="btn btn-danger custom-width">Remove</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div data-ng-controller="ModalInstanceController as modalCtrl">
            <script type="text/ng-template" id="modalContent">
                <div class="modal-header">
                    <h3 class="modal-title">Delete user</h3>
                </div>
                <div class="modal-body">
                    <label class="control-lable">Are you sure wan't to delete user {{userName}}?</label>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" id="ok" type="button" ng-click="$close()">OK</button>
                    <button class="btn btn-warning" id="cancel" type="button" ng-click="$dismiss('cancel')">Cancel</button>
                </div>
            </script>
        </div>

        <script src="<c:url value='/static/js/vendor/angular.js' />"></script>
        <script src="<c:url value='/static/js/vendor/jquery.min.js' />"></script>
        <script src="<c:url value='/static/js/vendor/ui-bootstrap-tpls-1.3.2.js' />"></script>
        <script src="<c:url value='/static/js/vendor/bootstrap.min.js' />"></script>
        <script src="<c:url value='/static/js/app.js' />"></script>
        <script src="<c:url value='/static/js/service/UserService.js' />"></script>
        <script src="<c:url value='/static/js/controller/UserController.js' />"></script>
        <script src="<c:url value='/static/js/controller/ModalInstanceController.js' />"></script>
    </body>
</html>