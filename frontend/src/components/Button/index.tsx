import styled from '@emotion/styled';
import { ReactNode } from 'react';

import theme from '@/styles/theme';

const lightFontColors: string[] = [
  theme.color.black,
  theme.color.gray,
  theme.color.blue,
  theme.color.green,
];

const ButtonWrapper = styled.div`
  border: none;
  background-color: transparent;
`;

const ButtonContent = styled.button`
  border: none;
  cursor: pointer;
  border-radius: 24px;
  margin: auto;
  background-color: ${(prop) => prop.color};
  color: ${(prop) =>
    lightFontColors.includes(prop.color ?? 'none')
      ? theme.color.white
      : theme.color.black};
  font-size: ${theme.fontSize.medium};
`;

const LargeButton = styled(ButtonContent)`
  width: 300px;
  height: 40px;
`;

const SmallButton = styled(ButtonContent)`
  width: auto;
  height: 40px;
  padding: 0 20px;
`;

const SvgButton = styled(ButtonContent)`
  border-radius: 0;
  background-color: transparent;
  margin: 0;
`;

interface ButtonProps {
  type: 'submit' | 'reset' | 'button';
  children?: ReactNode;
  size?: 'small' | 'large';
  color?: string;
  disabled?: boolean;
  onClick?: () => void;
  svg?: ReactNode;
}

const Button = ({
  children,
  disabled,
  onClick,
  size,
  color,
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
        <SmallButton
          type={type}
          color={color}
          disabled={disabled}
          onClick={onClick}
        >
          {children}
        </SmallButton>
      </ButtonWrapper>
    );
  }

  return (
    <ButtonWrapper>
      {/* eslint-disable-next-line react/button-has-type */}
      <LargeButton
        type={type}
        color={color}
        disabled={disabled}
        onClick={onClick}
      >
        {children}
      </LargeButton>
    </ButtonWrapper>
  );
};

export default Button;
