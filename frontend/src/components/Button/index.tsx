import styled from '@emotion/styled';
import { ReactNode } from 'react';

const ButtonWrapper = styled.div`
  border: none;
  background-color: transparent;
`;

const ButtonContent = styled.button`
  border: none;
  background-color: black;
  cursor: pointer;
  border-radius: 5px;
  margin: auto;
`;

const LargeButton = styled(ButtonContent)`
  width: 300px;
  height: 40px;
`;

const SmallButton = styled(ButtonContent)`
  width: 150px;
  height: 40px;
`;

interface ButtonProps {
  children: ReactNode;
  disabled?: boolean;
  onClick?: () => void;
  size: 'small' | 'large';
  type: 'submit' | 'reset' | 'button';
}

const Button = ({ children, disabled, onClick, size, type }: ButtonProps) => {
  if (size === 'small') {
    return (
      <ButtonWrapper>
        {/* eslint-disable-next-line react/button-has-type */}
        <SmallButton type={type} disabled={disabled} onClick={onClick}>
          {children}
        </SmallButton>
      </ButtonWrapper>
    );
  }
  return (
    <ButtonWrapper>
      {/* eslint-disable-next-line react/button-has-type */}
      <LargeButton type={type} disabled={disabled} onClick={onClick}>
        {children}
      </LargeButton>
    </ButtonWrapper>
  );
};

export default Button;
