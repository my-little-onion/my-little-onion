import styled from '@emotion/styled';
import { ChangeEvent, useState } from 'react';
import { useSetRecoilState } from 'recoil';

import { onionIndexState } from '@/pages/choose/store';
import { createOnion } from '@/services/onion';

const InputWrapper = styled.div`
  width: 100%;
  margin-top: 16px;
  display: flex;
  gap: 16px;
`;

const NameInput = styled.input`
  height: 28px;
  outline: none;
  width: 40vw;
  max-width: 200px;
`;

const CreateButton = styled.button`
  height: 28px;
  background-color: black;
  color: white;
  border: none;
  border-radius: 2px;
  padding: 0 8px;
  cursor: pointer;
`;

interface OnionNameInput {
  indexToSet: number;
  fetchData: () => void;
  closeModal: () => void;
}

const OnionNameInput = ({
  indexToSet,
  fetchData,
  closeModal,
}: OnionNameInput) => {
  const [onionName, setOnionName] = useState<string>('');
  const setOnionIndex = useSetRecoilState(onionIndexState);

  const handleCreateClick = async () => {
    await createOnion(onionName);
    closeModal();
    setOnionIndex(indexToSet);
    fetchData();
  };

  const handleNameInput = (event: ChangeEvent<HTMLInputElement>) => {
    if (event.target.value.length > 15) {
      event.target.value = event.target.value.slice(0, 15);
    }
    setOnionName(event.target.value);
  };

  return (
    <InputWrapper>
      <NameInput
        type='text'
        onChange={handleNameInput}
        value={onionName}
        placeholder='15자 이내로 말해줘'
      />
      <CreateButton type='button' onClick={handleCreateClick}>
        생성
      </CreateButton>
    </InputWrapper>
  );
};

export default OnionNameInput;
