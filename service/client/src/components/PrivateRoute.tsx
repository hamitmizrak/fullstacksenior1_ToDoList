import React from "react";
import { Navigate } from "react-router-dom";
import { checkTokenOnLocalStorage } from "../utils/Auth";
import PermissionsGate from "./PermissionGate";
import ForbiddenPage from "../pages/ForbiddenPage";

interface PrivateRouteProps {
  path: string;
  children: JSX.Element;
  redirectPath?: string;
  scopes?: string[];
}

const PrivateRoute: React.FC<PrivateRouteProps> = (props: PrivateRouteProps) => {
  const isAuthenticated = checkTokenOnLocalStorage();

  if (isAuthenticated && props.redirectPath !== undefined) {
    return <Navigate to={props.redirectPath} replace />;
  }

  return (
    <PermissionsGate pagePermissions={props.scopes} error={props.path === "/todo-list"? <ForbiddenPage/> : undefined}>
      {props.children}
    </PermissionsGate>
  );
};

export default PrivateRoute;
