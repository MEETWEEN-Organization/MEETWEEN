import styled from "@emotion/styled";
import { ReactNode } from "react";

const StyledButton = styled.button`
  padding: 8px 16px;
  background-color: red;
`;

interface IButton {
  variant: "primary" | "secondary" | "outline" | "default";
  size: "large" | "medium" | "small";
  children: ReactNode;
}

const Button = ({ variant, size, ...props }: IButton) => {
  return <StyledButton>{props.children}</StyledButton>;
};

export default Button;
