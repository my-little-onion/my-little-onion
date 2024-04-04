import styled from '@emotion/styled';

const MainBackground = styled.main`
  background-image: url('/images/backgrounds/main-background.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 100svh;
`;

interface BackgroundProps {
  children: React.ReactNode;
}

const Background = ({ children }: BackgroundProps) => {
  return <MainBackground>{children}</MainBackground>;
};

export default Background;
