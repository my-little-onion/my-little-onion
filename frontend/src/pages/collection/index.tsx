import styled from '@emotion/styled';

const MainBackground = styled.main`
  background-image: url('/images/backgrounds/main-background.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 100svh;
`;

const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 12vh;
`;

const Title = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 180px;
  height: 50px;
  background-color: #007fff;
  border-radius: 20px;
  color: white;
  font-size: 25px;
`;

const CollectionWrapper = styled.div`
  display: flex;
  justify-content: center;
  height: 85vh;
`;

const Collection = styled.div`
  position: relative;
  background-color: rgb(255, 255, 255, 0.6);
  width: 90%;
  height: 95%;
  border-radius: 20px;
`;

const RateWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 10%;
`;

const Rate = styled.div`
  display: flex;
  justify-content: center;
  background-color: #b9b9b9;
  width: 45%;
  height: 80%;
  border-radius: 25px;
  color: white;
  font-size: 20px;
`;

const RateString = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60%;
  height: 100%;
  background-color: #007fff;
  border-radius: 25px;
`;

const RateNumber = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50%;
`;

const MainPage = () => {
  return (
    <MainBackground>
      <TitleWrapper>
        <Title>
          <span>양파 도감</span>
        </Title>
      </TitleWrapper>
      <CollectionWrapper>
        <Collection>
          <RateWrapper>
            <Rate>
              <RateString>달성률</RateString>
              <RateNumber>66%</RateNumber>
            </Rate>
          </RateWrapper>
        </Collection>
      </CollectionWrapper>
    </MainBackground>
  );
};

export default MainPage;
