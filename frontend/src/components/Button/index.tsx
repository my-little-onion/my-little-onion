import styled from '@emotion/styled';
import { ReactNode } from 'react';

const ButtonWrapper = styled.div`
  border: none;
  background-color: transparent;
`;

const ButtonContent = styled.button`
  border: none;
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

const SvgButton = styled(ButtonContent)`
  border-radius: 0;
  background-color: transparent;
`;

interface ButtonProps {
  type: 'submit' | 'reset' | 'button';
  children?: ReactNode;
  size?: 'small' | 'large';
  disabled?: boolean;
  onClick?: () => void;
  svg?: ReactNode;
}

const Button = ({
  children,
  disabled,
  onClick,
  size,
  type,
  svg,
}: ButtonProps) => {
  if (svg) {
    return <SvgButton onClick={onClick}>{svg}</SvgButton>;
  }

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
